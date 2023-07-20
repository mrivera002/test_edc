/**
 * 
 */
package com.qr.document.confia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.qr.document.confia.model.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author mrivera
 *
 */
@Controller
@Slf4j
public class CarnetController {
	
	@Autowired
	Support supVal;
	
	@RequestMapping(value="/docQRCarnet")
	public ModelAndView getBitacoraCarnet(@RequestParam(value="cod") String cod) {
		
		final String urlQr = supVal.getUrlQr();
		final String urlDecoder = supVal.getUrlDecoder();
		final String urlToken = supVal.getUrlToken();
		final ModelAndView modelAndView = new ModelAndView();
		final WebClient.Builder builder = WebClient.builder();
		final ObjectMapper mapper = JsonMapper.builder()
	    		.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true)
	    		.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
	    		.build();
		Bitacora bitacora = new Bitacora();
		
		try {
			
			//1) Se reciben los datos encriptados
			final ResponseEncoded re = builder.build().get()
					.uri(urlQr.concat(cod))
					.retrieve()
					.bodyToMono(ResponseEncoded.class)
					.block();
			
			if (!re.isSuccess()) {
				bitacora.setSuccess(re.isSuccess());
				bitacora.setMessage(re.getMensaje());
				modelAndView.addObject("bit", bitacora);
				return modelAndView;
			}
			
			//2) Generacion de token para consumo del servicio de desincriptar
			final MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
			formData.add("client_id", supVal.getClientId());
			formData.add("grant_type", supVal.getGrantType());
			formData.add("username", supVal.getUserName());
			formData.add("password", supVal.getPassword());
			
			final ResponseToken rt = builder.build().post()
					.uri(urlToken)
					.accept(MediaType.APPLICATION_FORM_URLENCODED)
					.body(BodyInserters.fromFormData(formData))
					.retrieve()
					.bodyToMono(ResponseToken.class)
				    .block();
			
			//3) Se desincripta los datos
			final ResponseDecode rd = builder.build().post()
					.uri(urlDecoder.concat(re.getPromocion()))
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + rt.getAccessToken())
					.contentType(MediaType.TEXT_PLAIN)
					.body(BodyInserters.fromValue(re.getDetalle()))
					.retrieve()
					.bodyToMono(ResponseDecode.class)
					.block();   
			
			if (!rd.isExito()) {
				log.warn(rd.getMensaje());
				bitacora.setSuccess(rd.isExito());
				bitacora.setMessage("Hubo error en la consulta, favor intente más tarde..");
				modelAndView.addObject("bit", bitacora);
				return modelAndView;
			}
			
			//4) Asignacion del resultado del servicio a la clase bitacora para mostrar al usuario final en pantalla
			bitacora = mapper.readValue(rd.getData().toString(), Bitacora.class);
			modelAndView.addObject("bit", bitacora);
			
		} catch(Exception e) {
			log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()+"-->"+e.getMessage());
			bitacora = new Bitacora();
			bitacora.setSuccess(false);
			bitacora.setMessage("Hubo error en la consulta, favor intente más tarde..");
			modelAndView.addObject("bit", bitacora);
			return modelAndView;
		}
		
		return modelAndView;	
	}
}
