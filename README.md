
# LudoFun ✏️

Este proyecto se basa en el complemento **DataNucleus** y  **Maven**. Compruebe la configuración de la base de datos en el archivo *datanucleus.properties* y la dependencia del controlador JDBC especificada en el archivo *pom.xml*. Además, el proyecto contiene servidor y cliente.
## Test 🧪
Comprueba los tests con 
	
	mvn test

## Ejecución 🤖
Primero, crea la BD con el archivo sql/create-ludofunDB.sql
Luego puedes inicializar el proyecto con una sola línea

	mvn clean compile datanucleus:enhance datanucleus:schema-create jetty:run
	
Por separado, los comandos hacen lo siquiente:
> Elimina los archivos y carpetas generados por una compilación previa de un proyecto Maven.

	mvn clean
 > Compilar el código fuente de un proyecto Maven.

	mvn compile
> Prepara las clases para ser añadidas a la base de datos.

	mvn datanucleus:enhance
> Crea un esquema de base de datos.

	mvn datanucleus:schema-create
>Para iniciar el servidor, ejecuta el comando

	mvn jetty:run

Luego, el cliente se puede ejecutar en una nueva ventana de comandos con

	mvn exec:java -Pclient
