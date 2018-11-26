package com.kuspit.api.starter.network;

/**
 * Interfaz que define los m&eacute;todos que 
 * se utilizar&aacute;n para acceder a la API de Kuspit
 *
 */
public interface KuspitApiConnector {
	/**
	 * Se conecta a la API de Kuspit y consume el servicio para obtener el primer
	 * Token del usuario
	 * 
	 * @param code
	 * 		code obtenido durante el proceso de vinculaci&oacute;n
	 * @param clientId
	 * 		Client Id de la empresa
	 * @param clientSecret
	 * 		Client secret de la empresa
	 * @param redirectUri
	 * 		Redirect Uri de la empresa
	 * 
	 * @return
	 * 		Json con la respuesta obtenida desde la API de Kuspit
	 * 
	 * @throws Exception
	 * 		Devuelve alguna excepción en caso de haber existido algún error
	 */
	public String getJsonFirstToken(String code, String clientId, String clientSecret, String redirectUri) throws Exception;

	
}
