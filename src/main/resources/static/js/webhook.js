/**
 * Este archivo contiene el código javascript que se utilizará en el archivo webhook.html
 */

//Se registra el evento que se ejecutará una vez que se cierre la ventana de forma automática
window.onbeforeunload = function(){
	
	//Método que se ejecutará antes de cerrar la sesión, ver método closedOauth en archivo 
	//main.js
    window.opener.closedOauth();
    
    //Cierra la ventana
    self.close();
};

//Consume el endpoint para obtener el primer token del usuario
//y cierra la ventana al terminar
$.get("/webhook/process",function(){
	window.close();
})