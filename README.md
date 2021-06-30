# Web Market #
Api rest application developed for Web Market using java spring boot, spring security Oauth 2.0, Jpa, Hybernate, Swagger and Mysql.

## 1. Project structure ##

* The project is structured in the MVC model located in src / main / java / com / syphan / market /

* Config is the folder with the Spring security and Swagger configuration classes.

* Models is the folder with all model classes.

* Repository is the folder with all the interfaces for using JPA.

* Resource is the folder with all apis controllers.

* The connection information of database and the system user to generate the access token is located at src / main / resources / aplication.properties

## 2. Installation ##

* Open the project in your IDE. In my case, I used ECLIPSE.

* Run your mysql and import the market.sql database. 

* Enter your connection information in the project's application.properties file.

## 3. Running the Application ##

* After informing the database connection and changing the client-id and client-secret data to those of your choice, run the project through the ApirestApplication.java file.

* The application has the apis documentation through Swagger. To use, access the link http://localhost:8080/swagger-ui.html after the project has started.

* To make any request for the application, it is necessary to be authenticated. Authentication is done at http://localhost:8080/oauth/token. 

	* There will be 2 types of pre-registered users: ADMIN and USER. To access them, you will need to make a POST request with Basic Auth by entering the client-id and client-secret informed in apliccation.properties. In addition, a form-data with the keys: grant_type = password, user = (userEmail), password = (userPassword).

	* Admin account
		* grant_type: password
		* user: admin@admin.com
		* password: 123

	* User account
		* grant_type: password
		* user: user@user.com
		* password: 123

![Syphan](https://syphan.com.br/images/secret.png)
![Syphan](https://syphan.com.br/images/user.png)
		
* When you log in, you will receive an authentication token. This token must be informed as Bearer Authentication and must be informed in all requests within the application.

![Syphan](https://syphan.com.br/images/listCategories.png)

## Support ##
[gabriel@syphan.com.br](mailto:gabriel@syphan.com.br)
