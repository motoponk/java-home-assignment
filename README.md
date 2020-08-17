# Java Home Assignment

This home assignment is a simple product and order application developed using SpringBoot.

Anonymous access is allowed and CORS controls are disabled.

## Tech Stack
* Java
* SpringBoot 2.x
* H2(Dev) / Postgres (Prod)
* Spring Data JPA
* Swagger2
* SonarQube
* Jacoco
* Maven
* JUnit 5, Mockito, Testcontainers

## How to run?

### Run tests

`$ ./mvnw clean verify`

### Run application locally

`$ ./mvnw clean package & java -jar target/assignment-0.0.1-SNAPSHOT.jar`

### Creating images with buildpack (requires >= Spring Boot 2.3.0.M1)

Spring boot maven plugin has new goal to build image.

`$ ./mvnw spring-boot:build-image`

### Running using Docker

To start application with H2

`$ docker build -t product . && docker run -p8080:8080 -it product`

### Running using Docker Compose

To start application and Postgres

`$ mvn clean package && docker-compose -f docker-compose.yml up --build --force-recreate`

To stop

`$ docker-compose down`

### Run SonarQube analysis

```
$ ./run.sh sonar
$ ./mvnw clean verify -P sonar -Dsonar.login=$SONAR_LOGIN_TOKEN
```

## API

Swagger API documentation can be reeached.

* http://localhost:8080/v2/api-docs
* http://localhost:8080/swagger-ui.html

## TODO

* Enable security (JWT integration, OAuth2, etch)
* Add additional integration tests (postman integration test, Cucumber, Selenium, etc)
* Improve Test coverage
* Performance tests can be added (Gatlin or JMeter)
* Add scripts for build or deployment commands.
* I18n support
* Jenkinsfile
* Improve API Documentation
* SKU information for product can be gnerated automatically, or retreived from and external service.

## References

* https://spring.io/projects/spring-boot
* https://start.spring.io/
* https://prometheus.io/
* https://grafana.com/
* https://docs.docker.com/
* https://jenkins.io/doc/
* https://www.elastic.co/elk-stack
* https://gatling.io/
* https://www.sonarqube.org/
* https://sonarcloud.io/

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Accessing data with MySQL](https://spring.io/guides/gs/accessing-data-mysql/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
