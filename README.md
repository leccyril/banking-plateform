# Project Concepts

![Clean architecture.](/images/cleanarchi.webp)
### The main concepts and technologies used in this projects are :
* Hexagonal/clean architecture
* Reactive streams
* MongoDB
* Reactive Mongo
* SpringBoot 3.2
* Spring webflux
* Spring security 6
* Rest API
* Jwt authentication
* Junit tests
* Postman collections end to end test
* Lombok
* Docker for MongoDB database

The hexagonal architecture is same as clean architecture but adding port and adaptors and domain want should be totally agnostic about framework, often hexagonal architecture is called clean architecture.
In this hexagonal architecture we do not represent clearly ports and adaptors because it is really clear in the code what is what :

* Primary adaptors: Rest api controller. (in)
* secondary adaptors: Db and external services. (out)

monolithic application is used here because no so much code, bit with this architecture we can easily start to move authentication in a dedicated service.

### Benefit of using reactive projects
![Reactive technology.](/images/springreactive.png)

#### Comparaison between webflux and spring mvc
![comparaison.](/images/comparaison.png)

## Remarks

* We used mapper and dto just for the poc, in reality it is often not useful (but incoming object request is)
* In this project also we have a startup class will delete all collections and recreate some objects used in the postman collections
* Embedded Mongo database can be used but need more configurations
* MongoDb is used, maybe it is not the best solution as in this kind of application we should use a relational database with more integrity constraint, but it is simple and fast to put in place and do not need any schem update on ecah notification, and we wanted to point out the reactive streams with Reactive mongo that is a great technology.
* Rest api exceptions are managed since SpringBoot 3.2 in one Handler class : ApplicationControllerAdvice.java
* Lombok @Data is not used to not break POO principles
* We use conventional RestController annotations because it is more easy to code
* We also have an example of functional RestController used with webflux TestHandler /hello

# How to start project

## Launch database

You’ll start by editing this README file to learn how to edit a file in Bitbucket.

1. Go in /database folder in terminal
2. execute command :
```
docker-compose up -d
```

3. If need to delete in same folder do :
```
docker-compose down 
```
4. Access db viewer localhost:8081 (admin/pass)
5. You can modify as you want in both files docker-compose.yaml and the application.properties


## Launch project
### prerequites :

Maven, java

1. In terminal root project folder (pom.xml file level) run
```
mvn spring-boot:run
```
or
```
./mvnw spring-boot:run
```
or run
```
mvn clean install
```

and then 
```
java -jar [jarName].jar
```


2. You can access rest api doc on localhost:8086/api

## Test project

1. Explore http://localhost:8086/api to consult the Rest api documentation (Not anymore active cause webflux bug, still in progress)
2. Use in folder /postman the postman queries to add account, add beneficiaries, or execute transactions.
4. Import in postman environment variables and collection and just run it, token will be took automatically to fill header for following queries.
5. User with accounts is created in Startup class.

### Project health

Access to project health with this url : http://localhost:8086/api/actuator and for example to check if the service is up : http://localhost:8086/api/actuator/health
