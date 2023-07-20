/**
 * 
 */
package com.qr.document.confia.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mrivera
 *
 */
@Component
@Getter
@Setter
public class Support {
	
	@Value("${confia.qr.url}")
	private String urlQr;
	
	@Value("${confia.desencriptar.contenido.url}")
	private String urlDecoder;
	
	@Value("${confia.genera.token.url}")
	private String urlToken;
	
	@Value("${confia.client.id}")
	private String clientId;
	
	@Value("${confia.grant.type}")
	private String grantType;
	
	@Value("${confia.username}")
	private String userName;
	
	@Value("${confia.password}")
	private String password;
	
}
