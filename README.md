# Project Concepts

![Clean architecture.](/images/cleanarchi.webp)
### The main concepts and technologies used in this projects are :
* Hexagonal architecture
* Reactive streams
* MongoDB
* Reactive Mongo
* SpringBoot 3.2
* Rest API
* Jwt authentication
* Junit tests
* Postman collections
* Lombok

The hexagonal architecture is same as clean architecture but adding port and adaptors, often hexagonal architecture is called clean architecture.
In this hegaxonal architecture we do not represent clearly ports and adaptors because it is really clear in the code what is what :

* Primary adaptors: Rest api controller. (in)
* secondary adaptors: Db and external services. (out)

monolithic application is used here because no so much code, bit with this architecture we can easily start to move authentication in a dedicated service.

## Remarks

* We used mapper and dto just for the poc, in reality it is often not useful (but incoming object request is)
* In this project also we have a startup class will delete all collections and recreate some objects used in the postman collections
* Embedded Mongo database can be used but need more configurations
* MongoDb is used, maybe it is not th ebest solution as in this kind of application we should use a relational database with more integrity constraint, but it is simple and fast to put in place and do not need any schem update on ecah notification, and we wanted to point out the reactive streams with Reactive mongo that is a great technology.
* Rest api exceptions are managed since SpringBoot 3.2 in one Handler class : ApplicationControllerAdvice.java
* Lombok @Data is not used to not break POO principes

# How to start project

## Launch database

You’ll start by editing this README file to learn how to edit a file in Bitbucket.

1. Go in database folder in terminal
2. execute command :s
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
1. In terminal root project run
```
mvn spring-boot:run
```
2. You can access rest api doc on localhost:8086/api

## Test project

1. you can explore http://localhost:8086/api to consult the Rest api documentation
2. You can use in folder /postman the postman queries to add account, add beneficiaries, or execute transactions.

### Project health

You can access to project health with this url : http://localhost:8086/api/actuator and for example to check if the service is up : http://localhost:8086/api/actuator/health