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
public class ResponseEncoded implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean success;
	
    private String mensaje;
    
    private String promocion;
    
    private String detalle;

	@Override
	public String toString() {
		return "ResponseEncoded [success=" + success + ", mensaje=" + mensaje + ", promocion=" + promocion
				+ ", detalle=" + detalle + "]";
	}

}
