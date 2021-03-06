# Compte rendu du TP de BASTIDE Thomas
lien du GitHub: https://github.com/Toh-Ma/TP_API.git

Lancer: http://localhost:9090/greeting?nameGET=ENSIM


## TP2

### Cherchez une description succincte de chaque dépendance ajoutée pour trouver à quoi sert-elle:
Descriptions en Anglais:
- Spring web : 
 	Provides basic HTTP integration, including convenient servlet filters, Spring HTTP Invoker, an infrastructure to integrate with other web frameworks and HTTP 			technologies. 

- Spring data JPA :
	Java Persistence API is a collection of classes and methods to persistently store the vast amounts of data into a database.
	Spring Data JPA, part of the larger Spring Data family, makes it easy to easily implement JPA based repositories. This module deals with enhanced support for JPA based 	data access layers. 
	It makes it easier to build Spring-powered applications that use data access technologies.
	
- Hibernate validation : 
	Hibernate Validator allows to express and validate application constraints. The default metadata source are annotations, with the ability to override and extend through 	 the use of XML. 
	It is not tied to a specific application tier or programming model and is available for both server and client application programming.
	
- H2 database :
	H2 is a Java SQL database. The main features of H2 are:
	Very fast, open source, JDBC API
	Embedded and server modes; in-memory databases
	Browser based Console application
	Small footprint: around 2 MB jar file size
	
- Spring devtools : 
	Spring devtool is nothing but the developers tool which helps the developers to improve the time when we work with spring boot application, it is come with spring 1.3 		release, it basically takes the current changes which we make into the application and restart the server which turns help in improving the development process and 		provide a better development environment for developers.

- Thymeleaf : 
 	Thymeleaf is a modern server-side Java template engine for both web and standalone environments. 
	Thymeleaf's main goal is to bring elegant natural templates to your development workflow HTML that can be correctly displayed in browsers and also work as static 		prototypes, allowing for stronger collaboration in development teams.
	With modules for Spring Framework, a host of integrations with your favourite tools, and the ability to plug in your own functionality, Thymeleaf is ideal for modern-day 	  HTML5 JVM web development although there is much more it can do.

### Avec quelle partie du code avons-nous parametre l'url d'appel/greeting ?
 @GetMapping("/greeting")  
 
### Avec quelle partie du code avons-nous choisi le fichier HTML à afficher ?
 return "greeting";

### Comment envoyons-nous le nom à qui nous disons bonjour avec le second lien ?
 Via l'URL que l'on écrit comme ceci: http://localhost:9090/greeting?nameGET=ENSIM
 
 Remplacer la valeur par défaut avec @RequestParam -> @RequestParam(name ="nameGET", required =false, defaultValue ="World")

### Relancez-votre application, retournez sur la console de H2 : http://localhost:8080/h2-console. Avez-vous remarqué une différence ?
 La table Address et ses colonnes ont été ajoutées à la db ainsi qu'un onglet appele Sequences.
 
### Expliquez l'apparition de la nouvelle table en vous aidant de vos cours sur Hibernate, et de la dependance Hibernate de Spring.
 ORM qui fait la correspondance entre l'object et la db, il créé les requetes SQL.. la création de db ce fait avec les annotations.

### Relancez l'application, retournez sur la console H2 : http://localhost:8080/h2-console.
### Faites une requête de type SELECT sur la table Adress. Voyez-vous tout le contenu de data.sql ?
SELECT * FROM address  

ID  	CONTENT  	CREATION  
1	57 boulevard demorieux	2021-11-02 18:09:26.313861
2	51 allee du gamay, 34080 montpellier	2021-11-02 18:09:26.328821

### Pouvez-vous trouver à quoi sert l’annotation @Autowired du code précédent sur internet ?
L'annotation @ Autowired vous épargne le besoin de faire le câblage par vous-même dans le fichier XML (ou de toute autre manière) et trouve juste pour vous ce qui doit être injecté où, et le fait pour vous. Dans notre cas cela permet l'injection de dépendences pour pouvoir chercher l'interface AddressRepository. Ce service qui permet d'accéder à la base de donnée pour créer, lire, mettre à jour et supprimer les données.
 
Starting with Spring 2.5, the framework introduced annotations-driven Dependency Injection. The main annotation of this feature is @Autowired. It allows Spring to resolve and inject collaborating beans into our bean.

### Côté vu affichez la nouvelle donnée:
ID  	AUTOR  	CONTENT  	CREATION  
1	Zominique	57 boulevard demorieux	2021-11-09 16:10:03.820719
2	Thomas	51 allee du gamay, 34080 montpellier	2021-11-09 16:10:03.834943

### Ajouter de Bootstrap  
    <dependency>
        <groupId>org.webjars</groupId>
        <artifactId>bootstrap</artifactId>
        <version>5.1.3</version>
    </dependency>


## TP4

### Faut-il une clé API pour appeler MeteoConcept ?  
Oui, un token est nécessaire pour accéder aux bases de données de MeteoConcept.
mon token: d18f921d5f46154d423ff92b679e5b3bc7710647956ea5538e36d49aab4e0572

### Quelle URL appeler ?  
String uri = "https://api.meteo-concept.com/api/forecast/daily?"+"token=TOKEN&"+"latlng="+lat+","+lon;
Avec notre Token personnel.

### Quelle méthode HTTP utiliser ?
On doit utiliser la méthode **GET** et passer en paramètre les coordonnées géographique de l'endroit dont on souhaite obtenir les prévisions météos

### Comment passer les paramètres d'appels ?  
On saisie les coordonnées géographiques après latlng dans l'URL à appeler. 

### Où est l’information dont j’ai besoin dans la réponse :
- Pour afficher la température du lieu visé par les coordonnées GPS :
	![img1](https://user-images.githubusercontent.com/92999833/147581738-8b533692-a995-44f1-9e82-72b6a6883410.PNG)
	![image](img1.png)

- Pour afficher la prévision de météo du lieu visé par les coordonnées GPS :
	![img](https://user-images.githubusercontent.com/92999833/147581758-90ffac42-939e-4fcb-b46d-361af2d77afa.PNG)
	![image](img.png)













	




	

 


 
 


 
 




