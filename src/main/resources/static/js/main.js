/**
 * Este archivo contiene el código javascript que se utilizará en el archivo main.html
 */

/**
 * Evento del boton enviar
 * 
 * @param e
 * 		evento
 */
$("#btnEnviar").on("click",function(e){
	e.preventDefault();
	if(validarCampos()){ //Valida campos
		
		//Genera url de ventana de vinculacion en la API de Kuspit
		var url = $("#vinculacionForm").attr("action");
		url += "?idt="+$("#idEmpresa").val()
			+"&scope="+$("#scope").val()
			+"&response_type="+ $("#responseType").val()
			+"&redirect_uri="+ $("#redirectUri").val()
			+"&client_id="+ $("#clientId").val()
			+"&idexterno="+ $("#idExterno").val()
			+"&state="+ $("#state").val();
		
		console.log("Abriendo nueva ventana en url: "+url);
		//Abre la nueva ventana
		window.open(url, "Iniciar sesión en Kuspit", "height=800,width=1024");
	
		//Envia los datos para que sean guardados en sesión
		$.get("/registraDatos",{
			idt: $("#idEmpresa").val(),
			scope: $("#scope").val(),
			responseType: $("#responseType").val(),
			redirectUri: $("#redirectUri").val(),
			clientId: $("#clientId").val(),
			idExterno: $("#idExterno").val(),
			clientSecret: $("#clientSecret").val(),
			state: $("#state").val()
		});
	}
});

$("#btnNuevo").on("click",function(){
	toggleForm();
});
/**
 * Metodo que se ejecuta una vez que se ha cerrado de forma automática
 * la ventana de vinculación de Kuspit
 */
function closedOauth(){
	console.log("Se concluyó el proceso de vinculación con la API, procediendo a obtener los datos.")
	//Consume el endpoint para obtener la información de la vinculación
	//y el primer token del usuario
	$.get("/obtenInformacion",function(data){
		var h = "";
		
		//En caso de haber algún error, lo escribe en la pantalla.
		//En caso contrario, escribe los datos.
		if(data.error){
			h = $('<div class="row"><div class="col-xs-12 form-row"><h3 class="title2 nopad">Resultado</h3></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><h5 class="error">Ocurrió un error al tratar de consumir el servicio.</h5></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><label for="spanError">Mensaje de error:</label></div><div class="col-xs-12 form-row wrap-span"><span id="spanError">Lorem Ipsum</span></div></div>');
			$(h).find("#spanError").text(data.error);
		}else{
			h = $('<div class="row"><div class="col-xs-12 form-row"><h3 class="title2 nopad">La vinculaci&oacute;n se ha realizado de manera correcta y se obtuvieron los siguientes resultados.</h3></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><label for="spanCode">code:</label></div><div class="col-xs-12 form-row wrap-span"><span id="spanCode">Lorem Ipsum</span></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><label for="spanAccess">access_token:</label></div><div class="col-xs-12 form-row wrap-span"><span id="spanAccessToken">Lorem Ipsum</span></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><label for="spanContrato">contrato:</label></div><div class="col-xs-12 form-row wrap-span"><span id="spanContrato">Lorem Ipsum</span></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><label for="spanUsuario">usuario:</label></div><div class="col-xs-12 form-row wrap-span"><span id="spanUsuario">Lorem Ipsum</span></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><label for="spanExpiresIn">expires_in:</label></div><div class="col-xs-12 form-row wrap-span"><span id="spanExpiresIn">Lorem Ipsum</span></div></div>\
					<div class="row"><div class="col-xs-12 form-row"><label for="spanRefreshToken">refresh_token:</label></div><div class="col-xs-12 form-row wrap-span"><span id="spanRefreshToken">Lorem Ipsum</span></div></div>');
			$(h).find("#spanCode").text(data.code);
			$(h).find("#spanAccessToken").text(data.token.accessToken);
			$(h).find("#spanContrato").text(data.token.contrato);
			$(h).find("#spanUsuario").text(data.token.usuario);
			$(h).find("#spanExpiresIn").text(data.token.expiresIn);
			$(h).find("#spanRefreshToken").text(data.token.refreshToken);
		}
				
		$("#divResultado").empty().append(h);
		toggleForm();
	});
}
/**
 * Valida que los campos no se encuentren vacíos
 * 
 * @returns 
 * 		true cuando no hay ningun campo vacío
 */
function validarCampos(){
	if(isEmpty($("#idEmpresa").val())){
		BootstrapDialog.alert({title: 'Información', message: 'El identificador de empresa no puede ir en blanco',onhidden:function(){$("#idEmpresa").focus();}});
		return false
	}
	if(isEmpty($("#scope").val())){
		BootstrapDialog.alert({title: 'Información', message: 'El Scope no puede ir en blanco',onhidden:function(){$("#scope").focus();}});
		return false
	}
	if(isEmpty($("#responseType").val())){
		BootstrapDialog.alert({title: 'Información', message: 'El Response Type no puede ir en blanco',onhidden:function(){$("#responseType").focus();}});
		return false
	}
	if(isEmpty($("#redirectUri").val())){
		BootstrapDialog.alert({title: 'Información', message: 'El Redirect Uri no puede ir en blanco',onhidden:function(){$("#redirectUri").focus();}});
		return false
	}
	if(isEmpty($("#clientId").val())){
		BootstrapDialog.alert({title: 'Información', message: 'El Client Id no puede ir en blanco',onhidden:function(){$("#clientId").focus();}});
		return false
	}
	if(isEmpty($("#idExterno").val())){
		BootstrapDialog.alert({title: 'Información', message: 'El Identificador Externo no puede ir en blanco',onhidden:function(){$("#idExterno").focus();}});
		return false
	}
	if(isEmpty($("#state").val())){
		BootstrapDialog.alert({title: 'Información', message: 'El State no puede ir en blanco',onhidden:function(){$("#state").focus();}});
		return false
	}
	
	return true;
}
function isEmpty(val){
    return !$.trim(val);
}
function toggleForm(){
	$("#divDatos").toggle();
	$("#divForm").toggle();
	
}