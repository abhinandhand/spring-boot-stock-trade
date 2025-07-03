# Stock trade app

## Framework used

1. Java 21
2. Spring Boot 3.3.1
3. Maven 3.9.0 
4. For codebase concise and readability, used Lombok and Mapstruct.

## Start up command

```
mvn clean install
mvn spring-boot:run
```
Application will be availble on http://localhost:8080/

## Considerations and assumptions

1. Since the requirement was not to persist in any external DB, the developer chose in memory database, H2 for both application and test.


# Production ready considerations:

* For monitoring of the API's, Actuator endpoints are made available to capture health http://localhost:8080/actuator/health . Configuration is not yet enabled for metrics due to time constraint. 
* Spring security can be enabled for prod with roles for different endpoint.
* Passwords for the database is currently hardcoded in the code or property file as there is no vault integration done and in real time it can be fetched from secure vaults

