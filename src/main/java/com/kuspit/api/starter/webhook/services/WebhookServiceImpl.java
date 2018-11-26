package com.kuspit.api.starter.webhook.services;

import java.util.Map;

import javax.sql.rowset.spi.TransactionalWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuspit.api.starter.main.vo.VinculacionDataVo;
import com.kuspit.api.starter.network.KuspitApiConnector;
import com.kuspit.api.starter.webhook.vo.TokenInformationVo;

/**
 * Service que implementa los m&eacute;todos de la interfaz
 * del webhook.
 */

@Service
public class WebhookServiceImpl implements WebhookService{
	
	/** Servicio encargado de realizar conexiones con la API de Kuspit **/
	@Autowired
	private KuspitApiConnector apiConnector;
	
	/** Mapper utilizado para serializar y deserializar JSON **/
	@Autowired
	private ObjectMapper mapper;
	
	/** Variable de Log **/
	Logger logger = LoggerFactory.getLogger(WebhookServiceImpl.class);

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TokenInformationVo getFirstToken(String code, VinculacionDataVo vinculacionData) throws Exception {
		
		//Obtiene el Json que se tiene al obtener el primer token del usuario 
		String json = apiConnector.getJsonFirstToken(code,vinculacionData.getClientId(),vinculacionData.getClientSecret(),vinculacionData.getRedirectUri());
		
		//Se crea nuevo objeto de información de Token y 
		//se llenan sus propiedades con los datos del json.
		TokenInformationVo token = new TokenInformationVo();
		Map<String, Object> info = mapper.readValue(json, new TypeReference<Map<String,Object>>(){});
		token.setAccessToken(info.get("access_token").toString());
		token.setContrato(info.get("contrato").toString());
		token.setExpiresIn(Integer.parseInt(info.get("expires_in").toString()));
		token.setRefreshToken(info.get("refresh_token").toString());
		token.setUsuario(info.get("usuario").toString());

		//Se devuelve el objeto generado
		return token;
	}

}
