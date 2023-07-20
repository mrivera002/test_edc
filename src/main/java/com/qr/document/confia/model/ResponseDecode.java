/**
 * 
 */
package com.qr.document.confia.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mrivera
 *
 */
@Getter
@Setter
public class ResponseDecode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean exito;
	
    private String mensaje;
    
    private transient Object data;

}
