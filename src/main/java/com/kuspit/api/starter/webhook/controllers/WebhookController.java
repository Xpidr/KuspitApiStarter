package com.kuspit.api.starter.webhook.controllers;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.kuspit.api.starter.main.vo.VinculacionDataVo;
import com.kuspit.api.starter.webhook.services.WebhookService;
import com.kuspit.api.starter.webhook.vo.TokenInformationVo;

/**
 * Controller que se encargar&aacute; de atender las peticiones
 * recibidas desde el webhook
 *
 */

@Controller
public class WebhookController {

	/** Service del webhook **/
	@Autowired
	private WebhookService webhookService;
	
	/** Variable de Log **/
	Logger logger = LoggerFactory.getLogger(WebhookController.class);
	
	/**
	 * M&eacute;todo que atiende las peticiones del endpoint webhook.
	 * Toma los par&aacute;metros de la petici&oacute;n y los guarda
	 * en la sesi&oacute;n
	 * 
	 * @param code
	 * 		Code recibido por el webhook
	 * @param state
	 * 		State recibido por el webhook
	 * @param idExterno
	 * 		idExterno recibido por el webhoook
	 * @param session
	 * 		Sesi&oacute;n actual injectada por Spring
	 * 
	 * @return
	 * 		Model And View del html enviado en la p&aacute;gina
	 */
	@RequestMapping("/webhook")
	public ModelAndView webhook(@RequestParam("code")String code, @RequestParam("state")String state, @RequestParam("idExterno")String idExterno, HttpSession session) {
		
		logger.info("*************************************");
		logger.info("Recibiendo request a /webhook");
		logger.info("Parámetros recibidos: ");
		logger.info("code: "+code);
		logger.info("idExterno: "+idExterno);
		logger.info("state: "+state);
		
		ModelAndView modelAndView = new ModelAndView("webhook");
		session.setAttribute("code", code);
		session.setAttribute("idExterno", idExterno);
		session.setAttribute("state", state);
		return modelAndView;
	}

	/**
	 * M&eacute;todo que atiende las peticiones del endpoint webhook/process.
	 * Se encarga de conectar con la API de Kuspit, y obtener el primer Token
	 * del usuario con los par&aacute;metros que se tienen en sesi&oacute;n.
	 * 
	 * @param session
	 * 		Sesi&oacute;n inyectada por Spring
	 * 
	 * @return
	 * 		Siempre devuelve Json con la respuesta correcta para que se cierre
	 * 		la ventana correspondiente.
	 */
	@RequestMapping("/webhook/process")
	public @ResponseBody String procesarWebhook(HttpSession session) {
		logger.info("*************************************");
		logger.info("Recibiendo request a /webhook/process");
		
		TokenInformationVo tokenInfomationVo;
		try {
			//Obtiene el primer Token del usuario desde la API de Kuspit.
			tokenInfomationVo = webhookService.getFirstToken(String.valueOf(session.getAttribute("code")),(VinculacionDataVo)session.getAttribute("vinculacionInfo"));

			logger.info("Poniendo datos de token en la sesión");
			//Guarda el Token en la sesión
			session.setAttribute("tokenInfo", tokenInfomationVo);
			
		} catch (Exception e) {
			//En caso de que haya habido algún error, lo pone en la sesión con la etiqueta 'error'
			e.printStackTrace();
			session.setAttribute("error", e.getMessage());
		}
		
		//Devuelve una respuesta correcta
		return "{'respuesta':'ok'}";
	}
}
