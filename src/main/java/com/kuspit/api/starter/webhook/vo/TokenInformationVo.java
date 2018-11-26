package com.kuspit.api.starter.webhook.vo;

/**
 * Objeto que se utilizar&aacute;n para almacenar
 * los datos del primer token del usuario.
 *
 */

public class TokenInformationVo {
	/** Primer token del usuario **/
	private String accessToken;
	
	/** N&uacute;mero de contrato del usuario **/
	private String contrato;
	
	/** Identificador de usuario **/
	private String usuario;
	
	/** Tiempo en el que expirar&aacute; el token **/
	private int expiresIn;
	
	/** Refresh token que se utilizar&aacute; para obtener m&aacute;s tokens **/
	private String refreshToken;
	
	/* Getters y Setters **/
	
	/**
	 * @return the accessToken
	 */
	public String getAccessToken() {
		return accessToken;
	}
	/**
	 * @param accessToken the accessToken to set
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	/**
	 * @return the contrato
	 */
	public String getContrato() {
		return contrato;
	}
	/**
	 * @param contrato the contrato to set
	 */
	public void setContrato(String contrato) {
		this.contrato = contrato;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the expiresIn
	 */
	public int getExpiresIn() {
		return expiresIn;
	}
	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	/**
	 * @return the refreshToken
	 */
	public String getRefreshToken() {
		return refreshToken;
	}
	/**
	 * @param refreshToken the refreshToken to set
	 */
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	
	
}
