package com.kuspit.api.starter.main.vo;

/**
 * Objeto que contiene los datos de la vinculación para
 * almacenarlos en la sesión
 *
 */

public class VinculacionDataVo {

	/** Identificador de la empresa **/
	private String idt;
	/** Scope de la empresa **/
	private String scope;
	/** Response type de la petición **/
	private String responseType;
	/** Redirect Uri de la empresa **/
	private String redirectUri;
	/** Client Id de la empresa **/
	private String clientId;
	/** Client secret de la empresa **/
	private String clientSecret;
	/** Identificador externo que se asignó por la empresa para este cliente **/
	private String idExterno;
	/** State que asignó la empresa para este cliente **/
	private String state;
	/**
	 * @return the idt
	 */
	public String getIdt() {
		return idt;
	}
	/**
	 * @param idt the idt to set
	 */
	public void setIdt(String idt) {
		this.idt = idt;
	}
	/**
	 * @return the scope
	 */
	public String getScope() {
		return scope;
	}
	/**
	 * @param scope the scope to set
	 */
	public void setScope(String scope) {
		this.scope = scope;
	}
	/**
	 * @return the responseType
	 */
	public String getResponseType() {
		return responseType;
	}
	/**
	 * @param responseType the responseType to set
	 */
	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}
	/**
	 * @return the redirectUri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}
	/**
	 * @param redirectUri the redirectUri to set
	 */
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}
	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/**
	 * @return the idExterno
	 */
	public String getIdExterno() {
		return idExterno;
	}
	/**
	 * @param idExterno the idExterno to set
	 */
	public void setIdExterno(String idExterno) {
		this.idExterno = idExterno;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}
	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	
	
}
