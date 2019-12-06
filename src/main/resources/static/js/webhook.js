/**
 * Este archivo contiene el código javascript que se utilizará en el archivo webhook.html
 */

//Se registra el evento que se ejecutará una vez que se cierre la ventana de forma automática
window.onbeforeunload = function(){
	
    // Manda llamar la funcion en la ventana principal cuando se cierra la ventana
    // Se maneja con setTimeout para solventar un problema de Chrome 
    // que no realiza correctamente el onbeforeunload
    setTimeout(function() {
        //Método que se ejecutará antes de cerrar la sesión, ver método closedOauth en archivo 
	//main.js
        window.opener.closedOauth();
    
        //Cierra la ventana
        self.close();
    }, 0);
};

//Consume el endpoint para obtener el primer token del usuario
//y cierra la ventana al terminar
$.get("/webhook/process",function(){
	window.close();
})
