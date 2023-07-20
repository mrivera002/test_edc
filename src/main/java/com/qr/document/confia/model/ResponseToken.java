/**
 * 
 */
package com.qr.document.confia.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;

/**
 * @author mrivera
 *
 */
@Getter
@Setter
public class ResponseToken implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonAlias(value = "access_token")
	private String accessToken;
	
	@JsonAlias(value = "expires_in")
	private int expiresIn;
	
	@JsonAlias(value = "refresh_expires_in")
	private int refreshExpiresIn;
	
	@JsonAlias(value = "refresh_token")
	private String refreshToken;
	
	@JsonAlias(value = "token_type")
	private String tokenType;
	
	@JsonAlias(value = "not-before-policy")
	private int notBeforePolicy;
	
	@JsonAlias(value = "session_state")
	private String sessionState;
	
	private String scope;

	@Override
	public String toString() {
		return "ResponseToken [accessToken=" + accessToken + ", expiresIn=" + expiresIn + ", refreshExpiresIn="
				+ refreshExpiresIn + ", refreshToken=" + refreshToken + ", tokenType=" + tokenType
				+ ", notBeforePolicy=" + notBeforePolicy + ", sessionState=" + sessionState + ", scope=" + scope + "]";
	}

}
