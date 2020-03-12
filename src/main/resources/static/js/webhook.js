/**
 * Este archivo contiene el código javascript que se utilizará en el archivo webhook.html
 */

//Consume el endpoint para obtener el primer token del usuario,
//manda llamar la funcion de la ventana de origen para mostrar los
//datos del primer token y cierra la ventana al terminar
$.get("/webhook/process",function(){
	window.opener.closedOauth();
	window.close();
});
