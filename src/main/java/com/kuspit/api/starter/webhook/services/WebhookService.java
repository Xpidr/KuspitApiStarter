package com.kuspit.api.starter.webhook.services;

import com.kuspit.api.starter.main.vo.VinculacionDataVo;
import com.kuspit.api.starter.webhook.vo.TokenInformationVo;

/**
 * Interface que establece los m&eacute;todos correspondientes al webhook
 *
 */

public interface WebhookService {
	/**
	 * Obtiene el primer token del cliente para realizar operaciones
	 * @param code 
	 * 		C&oacute;digo recibido en la vinculaci&oacute;n del usuario
	 * @return 
	 * 		Objeto con los datos del token del usuario
	 * @throws Exception
	 * 		En caso de que haya habido alg&uacute;n error en la obtenci&oacute;n del token. 
	 */
	public TokenInformationVo getFirstToken(String code, VinculacionDataVo vinculacionData) throws Exception;

}
