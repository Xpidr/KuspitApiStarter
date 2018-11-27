# Kuspit API Starter

Kuspit API Starter es un sistema que permite realizar la primer parte del proceso de consumo de la API de Kuspit, esto es la vinculación de clientes a una empresa tercera y la obtención del primer token de operación de cada cliente que se registre a través de la plataforma.

Este proceso se realiza de la siguiente manera:

1.	Solicitar el registro de una empresa cuyo webhook sea http://localhost:8888/webhook (o el puerto configurado en Kuspit API Starter)
2.	Se descarga, configura, compila y despliega la aplicación Kuspit API Starter, contenida en este repositorio.
3.	Se accede a endpoint principal de Kuspit API Starter (http://localhost:8888) para ingresar los datos de la empresa.
4.	Kuspit API Starter abrirá una nueva ventana del navegador con el formulario de vinculación correspondiente a los datos ingresados. Realizar el proceso de vinculación proporcionando los datos que se solicitan en la nueva ventana.
5.	Al final de la vinculación, la ventana de vinculación de cliente redirigirá al webhook configurado (en este caso http://localhost:8888/webhook) que estará escuchando Kuspit API Starter.
6.	Kuspit API Starter cerrará la ventana y con los datos de la empresa y los de vinculación, obtendrá el primer token del cliente, el cual se mostrará en la ventana principal de Kuspit API Starter. Con este token se realizarán las acciones posteriores.

## Configuración

La configuración de Kuspit API Starter se encuentra en el archivo application.properties que se encuentra en la ruta src/main/resources/ del directorio de la aplicación.

Dentro del archivo mencionado se encuentran dos propiedades que se pueden configurar:

* *com.kuspit.api.url* – Corresponde al url donde se encuentra la API de Kuspit, el valor te será proporcionado una vez que se registre tu empresa y se debe de colocar de forma obligatoria. 
* *server.port* – Es el puerto en el cual se encuentra configurada la ejecución de Kuspit API Starter, por default el valor es 8888.

El campo para el URL de la API de Kuspit deberás de ingresarlo de forma obligatoria con la información que te proporcionemos. Si se quiere, se puede cambiar el puerto en el cual se desplegará la aplicación en este archivo.


### Prerequisitos

Lo que se requiere para compilar el sistema es lo siguiente:

* Conexión a internet
* Java JDK 8 o superior
* Maven 3.3 o superior
* Realizar las modificaciones en el archivo application.properties, según la sección anterior.

## Compilación

Una vez que se tenga el código descargado, desde el directorio principal de la aplicación, deberá de ejecutarse el siguiente comando para compilar:

```
mvn clean install
```

## Ejecución

Si la compilación se realiza de manera correcta, dentro de la carpeta target del proyecto se encontrará un archivo con el nombre starter-1.0.0.jar  para ejecutarlo, se deberá de utilizar el siguiente comando:

```
java -jar  starter-1.0.0.jar
```

## Uso

Una vez que el sistema se ha ejecutado, como se indica en la sección anterior. Se puede acceder al mismo desde la url: http://localhost:8888 (o el puerto configurado según la siguiente sección). Al acceder a través de un navegador, nos mostrará una pantalla como la siguiente:<br/>
<img src="http://test.kuspit.com:8080/Kuspit/img/okPortal/image001.jpg" width="50%" height="50%" />
 

En esta pantalla deberemos de dar ingresar los datos de la empresa justo tal y como te los enviamos:<br/>
<img src="http://test.kuspit.com:8080/Kuspit/img/okPortal/image003.png"/>

Donde:

* Identificador de Empresa. Es el id que le corresponde a tu empresa, proporcionado por Kuspit.
* Scope. Scope que le corresponde a tu empresa, proporcionado por Kuspit.
* Redirect Uri. Es el valor que se configuró para redirect uri al dar de alta a tu empresa, para utilizar Kuspit API Starter, este deberá de ser http://localhost:8888.
* Client Id. Es el valor de cliente id que se generó durante el proceso de alta de tu empresa, proporcionado por Kuspit.
* Client Secret. Es el valor de client secret que se generó durante el proceso de alta de tu empresa, proporcionado por Kuspit.
* Identificador externo. Es el id que la empresa le asigna al cliente que se va a registrar, este id deberá de ser único para cada uno de los clientes, generado por tu empresa.
* State. Valor que se le colocará como estatus al cliente que se va a registrar, es generado por tu empresa.

Una vez que se han ingresado los datos, se procederá a dar clic en el botón “Enviar campos”. Al hacerlo nos mostrará la ventana de vinculación de la API de Kuspit. Es importante mencionar que esta ventana no forma parte de Kuspit API Starter, ya que se está accediendo a la misma a través de la URL configurada en el archivo application.properties: 

A partir de este punto se procederá a realizar la vinculación en la nueva ventana:

a) Registra el correo del cliente a vincular:<br/>
<img src="http://test.kuspit.com:8080/Kuspit/img/okPortal/image007.png" width="50%" height="50%" />
 
b) Se enviará un código de activación al correo indicado, ingresa el mismo en esta parte:<br/>
<img src="http://test.kuspit.com:8080/Kuspit/img/okPortal/image009.png" width="50%" height="50%" />

c) Escribe una contraseña para acceder y confirma la misma:<br/>
<img src="http://test.kuspit.com:8080/Kuspit/img/okPortal/image011.png" width="50%" height="50%" />
 
d) Al dar clic al botón de vinculación se asociará el cliente a tu empresa y redirigirá al webhook que estableció al incio del proceso (http://localhost:8888/webhook), pasándo como parámetros el code y el status del cliente. Kuspit API Starter mostrará una ventana como la siguiente:<br/>
<img src="http://test.kuspit.com:8080/Kuspit/img/okPortal/image013.png" width="50%" height="50%" />
 

Esta ventana permanecerá durante el tiempo que tome el realizar la petición de primer Token. Al final del proceso se cerrará y se regresará a la ventana principal de Kuspit API Starter, en donde se mostrarán los datos generados durante la vinculación y la obtención de primer token del cliente, éstos serán los que se utilicen para realizar las operaciones de consumo de la API:<br/>
<img src="http://test.kuspit.com:8080/Kuspit/img/okPortal/image015.png" width="50%" height="50%" />

## Construido con

* [Spring Boot](http://spring.io/projects/spring-boot/) - Framework
* [Maven](https://maven.apache.org/) - Administración de dependencias
* [Bootstrap](https://getbootstrap.com/) - Framework front
* [JQuery](https://jquery.com/) - Librería Javascript

## Contacto

Cualquier duda, comentario, sugerencia, por favor dirígelas al siguiente correo electrónico: desarrollo@kuspit.com

## Changelog

Version 1.0 - Primera versión del sistema. 

## Licencia

Este proyecto está licenciado bajo la licencia MIT - ver el archivo LICENSE.md para más detalles.


