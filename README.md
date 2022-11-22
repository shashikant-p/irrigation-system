# Automated Irrigation System
This is sample implementation of an Automated Irrigation System writteen in Java using Spring Boot.

## Technology Stack
| Topic | Value |
| ------------ | ------------ |
| Database  |  MySQL |
| Language | Java |
| API Framework | Spring Boot |

## APIs

| Method | Endpoint |
| ------------ | ------------ |
| GET | /api/v1/plot |
| POST| /api/v1/plot |
| GET|  /api/v1/plot/{plotId}|
| PUT| /api/v1/plot/{plotId}|
| GET| /api/v1/plot/{plotId}/config|
| POST| /api/v1/plot/{plotId}/config|
| GET| /api/v1/plot/{plotId}/config/{plotConfigId}|
| PUT| /api/v1/plot/{plotId}/config/{plotConfigId}|

## Data Model

![er_diagram](https://user-images.githubusercontent.com/1014107/203373289-57d125fe-25cc-49e7-9d9a-b598ab8190a8.png)

## Setup

 - Clone the repository
 - Create a test database of the application in Mysql.
 - Update this database name along with the database credentials from the step above  in the application.properties file.
 - Open a terminal and navigate to the root directory of this application.
 - Exceure `mvn spring-boot:run`.

## Additional Notes

 - Once the applcation is executing, the Swagger API docs will be accessible at - http://localhost:8080/swagger-ui/index.html
 - Flyway is configured to create the database tables and insert a few plot data.
 - Code Quality can be found [here](https://app.codacy.com/gh/shashikant-p/irrigation-system/issues)
