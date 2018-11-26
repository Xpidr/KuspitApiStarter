package com.kuspit.api.starter.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class KuspitApiConnectorImp implements KuspitApiConnector {

	/** URL de la API de Kuspit **/
	@Value("${com.kuspit.api.url}")
	private String urlApiKuspit;
	
	/** Variable de Log **/
	Logger logger = LoggerFactory.getLogger(KuspitApiConnectorImp.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getJsonFirstToken(String code, String clientId, String clientSecret, String redirectUri) throws Exception {
		
		logger.info("Obteniendo primer token de cliente");
		
		// Ruta del servicio web consumido	
		String path = "OpenKuspit/api/token";
		String url = urlApiKuspit +(urlApiKuspit.endsWith("/")?"":"/") + path;

		// Se genera mapa con los parámetros del servicio web
		Map<String, String> params = new HashMap<>();
		params.put("grant_type", "authorization_code");
		params.put("client_id", clientId);
		params.put("client_secret", clientSecret);
		params.put("redirect_uri", redirectUri);
		params.put("code", code);

		// Se envía petición Post al URL indicado, incluyendo los parámetros y
		// devolviendo el resultado de la misma.
		return sendPostOperation(url, params);
	}

	/**
	 * Realiza una petición Post a la API de Kuspit
	 * 
	 * @param urlString
	 * 		Url absoluta de la API de Kuspit
	 * @param params
	 * 		Mapa con los par&aacute;metros que se pasar&aacute;n a la API de Kuspit
	 * @return
	 * 		Json que respondi&ooacute; la API de Kuspit
	 * @throws Exception
	 * 		En caso de que haya alg&uacute;n error
	 */
	private String sendPostOperation(String urlString, Map<String, String> params) throws Exception {
		String json = null;
		
		//Se genera un String que contenga los parámetros en formato de URL
		//para lo cual se cocatenarán los mismos de la forma: clave1=valor1&clave2=valor2& ... etcétera
		//*****************************************************
		//****************** MUY IMPORTANTE: ******************
		//Los valores deben de ir codificados en URLEncoding
		//******************************************************
		StringBuilder queryString = new StringBuilder();
		if (params != null && params.size() > 0) {
			boolean first = true;
			for (String key : params.keySet()) {
				if (!first) {
					queryString.append("&");
				} else {
					first = false;
				}
				//En esta parte agregan los parámetros y se codifican los valores con URLEncoder, ya que
				//Java no lo hace en automático.
				queryString.append(key).append("=").append(URLEncoder.encode(params.get(key)));
			}
		}

		logger.info("Haciendo nueva peticion a: "+ urlString);
		logger.info("QueryString: "+queryString.toString());
		if (urlString.startsWith("https")) { //En caso de conexiones HTTPS
			
			URL url = new URL(urlString);
			//Se crea la conexión.
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			
			//Se asignan los parámetros de la conexión.
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			
			//Encabezado necesario
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			//Se agrega el String con los parámetros
			byte[] postData = queryString.toString().getBytes();
			conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
			conn.getOutputStream().write(postData);
			conn.getOutputStream().flush();
			conn.getOutputStream().close();

			//Se ejecuta la conexión
			int responseCode = conn.getResponseCode();
			
			//Se lee el contenido de la respuesta y se guarda en el String json
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuffer output = new StringBuffer();
			String outputLine = null;
			while ((outputLine = br.readLine()) != null) {
				output.append(outputLine);
			}
			//Se guarda la respuesta en el objeto que se devuelve
			json = output.toString();

			//Se cierra la conexión.
			conn.disconnect();

			//En caso de que no se haya recibido 200 como código de respuesta, se
			//lanza excepción indicando el código de respuesta recibido y el
			//mensaje del request.
			if (responseCode != 200) {
				throw new Exception("Codigo de Respuesta: "+responseCode+(json!=null?" Mensaje: "+json:""));
			}
			
		} else { //La conexión no es HTTPS
			
			URL url = new URL(urlString);
			
			//Se crea la conexión
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			//Se agregan los parámetros de la conexión
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Accept", "application/json");
			
			//Encabezado necesario
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			
			//Se agrega string con los parámetros
			byte[] postData = queryString.toString().getBytes();
			conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
			conn.getOutputStream().write(postData);
			conn.getOutputStream().flush();
			conn.getOutputStream().close();

			//Se ejecuta la conexión
			int responseCode = conn.getResponseCode();
			
			//Se lee el contenido de la respuesta y se guarda en el String json
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			StringBuffer output = new StringBuffer();
			String outputLine = "";
			while ((outputLine = br.readLine()) != null) {
				output.append(outputLine);
			}

			//Se guarda la respuesta en el objeto que se devuelve
			json = output.toString();
			
			//Se cierra la conexión
			conn.disconnect();
			
			//En caso de que no se haya recibido 200 como código de respuesta, se
			//lanza excepción indicando el código de respuesta recibido y el
			//mensaje del request.
			if (responseCode != 200) {
				throw new Exception("Codigo de Respuesta: "+responseCode+(json!=null?" Mensaje: "+json:""));
			}
		}
		
		logger.info("Respuesta recibida: "+json);
		//Se devuelve el json en caso de que no haya habido problemas
		return json;

	}

}
