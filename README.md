## Launch database

You’ll start by editing this README file to learn how to edit a file in Bitbucket.

1. Go in database folder in terminal
2. execute command docker-compose up -d
3. If need to delete docker-compose down in same folder
4. Access db viewer localhost:8081 (admin/pass)


# Launch project
1. In terminal root project run 
'mvn spring-boot:run'
2. You can access rest api doc on localhost:8086/api


---

# Concepts

### The main concepts used in this projects are :
![Clean architecture.](/images/cleanarchi.webp) 
* Hexagonal architecture
* Reactive streams
* MongoDB
* Reactive Mongo
* SpringBoot 3.2
* Rest API

We used mapper and dto juste for the poc, in reality it is often not useful