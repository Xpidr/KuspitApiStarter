package com.kuspit.api.starter.main.controllers;

import java.net.URLDecoder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuspit.api.starter.main.vo.VinculacionDataVo;

/**
 * Controller que se encargar&aacute; de atender las peticiones recibidas
 * desde la pantalla principal
 * 
 */

@Controller
public class MainWebController {
	
	/**
	 * Valor del url de authorization2 de la API de Kuspit
	 */
	@Value("${com.kuspit.api.url}")
	private String urlApi;
	
	/** Variable de Log **/
	Logger logger = LoggerFactory.getLogger(MainWebController.class);

	/**
	 * Procesa la peticion a la pantalla principal de la aplicación
	 * 
	 * @return 
	 * 		ModelAndView para responder a la petición
	 */
	@RequestMapping("/")
	public ModelAndView pantallaPrincipal() {
		logger.info("Recibiendo request en /");
		
		//Ruta de url para la pantalla de vinculación
		String path = "OpenKuspit/api/authorization2";
		
		//Model and View que devuelven el archivo main.html
		ModelAndView modelAndView = new ModelAndView("main");
		
		//Se agrega como parámetro la url que corresponde a la vinculación
		//en la API Kuspit
		String urlVinculacion = urlApi+(urlApi.endsWith("/")?"":"/")+path;
		modelAndView.addObject("urlApi", urlVinculacion);
		logger.info("Devolviendo url vinculación: "+urlVinculacion);
		return modelAndView;
	}
	
	/**
	 * Endpoint que sirve para guardar datos en la sesi&oacute;n que ser&aacute;n 
	 * utilizados con posterioridad
	 * 
	 * @param idt
	 * 		Identificador de la empresa
	 * @param scope
	 * 		Scope de la empresa
	 * @param responseType
	 * 		Response type de la solicitud
	 * @param redirectUri
	 * 		Redirect Uri registrado por la empresa
	 * @param clientId
	 * 		Client Id de la empresa
	 * @param clientSecret
	 * 		Client Secret de la empresa
	 * @param idExterno
	 * 		Identificador Externo asignado por la empresa para este cliente
	 * @param state
	 * 		State que se pone al cliente
	 * @param session
	 * 		Objeto que contiene los datos de la sesi&oacute;n inyectado por Spring
	 * @return
	 */
	
	@RequestMapping("/registraDatos")
	public @ResponseBody String guardaDatos(
			@RequestParam("idt")String idt,
			@RequestParam("scope")String scope, 
			@RequestParam("responseType")String responseType,
			@RequestParam("redirectUri")String redirectUri, 
			@RequestParam("clientId")String clientId, 
			@RequestParam("clientSecret")String clientSecret, 
			@RequestParam("idExterno")String idExterno, 
			@RequestParam("state")String state,
			HttpSession session) {
		logger.info("*************************************");
		logger.info("Request recibido en /registraDatos");
		
		//Se genera un objeto con la información recibida en los parámetros
		VinculacionDataVo vinculacionDataVo = new VinculacionDataVo();
		vinculacionDataVo.setIdt(idt);
		vinculacionDataVo.setScope(scope);
		vinculacionDataVo.setResponseType(responseType);
		vinculacionDataVo.setRedirectUri(redirectUri);
		vinculacionDataVo.setClientId(URLEncoder.encode(clientId));		//Viene codificado desde el Front, se decodificará aquí
		vinculacionDataVo.setClientSecret(URLEncoder.decode(clientSecret));	//Viene codificado desde el Front, se decodificará aquí
		vinculacionDataVo.setIdExterno(idExterno);
		vinculacionDataVo.setState(state);
		
		logger.info("Datos recibidos:");
		logger.info("idt: " + idt);
		logger.info("scope: " + scope);
		logger.info("responseType: " + responseType);
		logger.info("redirectUri: " + redirectUri);
		logger.info("clientId: " + clientId);
		logger.info("clientSecret: " + clientSecret);
		logger.info("idExterno: " + idExterno);
		logger.info("state: " + state);
		
		//Se guarda el objeto generado en sesión para su posterior uso
		session.setAttribute("vinculacionInfo", vinculacionDataVo);

		return "{'resultado':'ok'}";
	}
	
	/**
	 * Este endpoint se consume una vez que se ha realizado la vinculación
	 * así como obtenido el Token del usuario registrado. Devuelve la información 
	 * tanto de la vinculación, el Token y el code de usuario
	 * 
	 * @param session
	 * 		Objeto que contiene los datos de la sesi&oacute;n inyectado por Spring
	 * 
	 * @return
	 * 		Json con los datos correspondientes a la vinculación, el token y el code 
	 * 		del usuario.
	 */
	@RequestMapping("/obtenInformacion")
	public @ResponseBody Map<String,Object> obtenInformacion(HttpSession session){
		Map<String,Object> info = new HashMap<>();
		
		logger.info("*************************************");
		logger.info("Request recibido en /obtenInformacion");
		
		if(session.getAttribute("error")!=null) {
			logger.info("Hubo un error al tratar de obtener el token: "+session.getAttribute("error"));
			info.put("error", session.getAttribute("error"));
		}else {
			logger.info("Devolviendo respuesta correcta con token y datos de vinculación");
			info.put("token", session.getAttribute("tokenInfo"));
			info.put("vinculacion", session.getAttribute("vinculacionInfo"));
			info.put("code", session.getAttribute("code"));
		}
		return info;
	}
}
