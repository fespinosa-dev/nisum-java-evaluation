# spring boot + JWT 

![example workflow](https://github.com/fespinosa-dev/nisum-java-evaluation/actions/workflows/gradle.yml/badge.svg)

## Environment:
- Java version: 11
- Gradle version: 7.3.3
- Spring Boot version: 2.6.3

## How to run
- First download the project:

```bash
git clone https://github.com/fespinosa-dev/nisum-java-evaluation.git
cd nisum-java-evaluation
```
- Compile the project:

```bash
./gradlew clean build
```
- Then run the app:
```bash
./gradlew bootRun
```
- To run the tests:
```bash
./gradlew test
```
## Operations:

### Create a new user

`POST` request to `/api/v1/users`:



Example of the payload to **create a new user**:
```json
{
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.do",
  "password": "0123456789$abcdefgAB",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    }
  ]
}
```
Example of a response:

```json
{
  "userId": "4e2198cc-7854-4d6e-926b-2d3c07b1e966",
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.do",
  "password": "0123456789$abcdefgAB",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    }
  ],
  "created": "2022-01-22T20:42:10.483026Z",
  "modified": "2022-01-22T20:42:10.483026Z",
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdCI6MTY0Mjg4NDEzMCwiZXhwIjoxNjQyOTcwNTMwfQ.ELZHmPcdnt...",
  "last_login": "2022-01-22T20:42:10.483026Z",
  "isactive": true
}
```

### Authenticate a user

`POST` request to `/auth/login`:


Example of the payload to **authenticate a user**:

```json
{
"username": "Juan Rodriguez",
"password": "0123456789$abcdefgAB"
}
```

Example of a response:

```json
{
  "username": "Juan Rodriguez",
  "token": "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdC...",
  "last_login": "2021-12-14T23:47:46.164577Z"
}
```
### Get all registered users

>Requires a generated token to access

`GET` request to `/api/v1/users`:

Example of the payload to get all users:

```json
[
  {
    "userId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.do",
    "password": "0123456789$abcdefgAB",
    "phones": [
      {
        "number": "1234567",
        "citycode": "1",
        "countrycode": "57"
      }
    ],
    "created": "2021-12-14T23:47:46.164577Z",
    "modified": "2021-12-14T23:47:46.164577Z",
    "token": "yJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdC...",
    "last_login": "2021-12-14T23:47:46.164577Z",
    "isactive": true
  }
]
```
## Swagger UI

Swagger UI: [http://localhost:8080/swagger-ui/index.html#/](http://localhost:8080/swagger-ui/index.html)

![Swagger UI](swagger.png)


### Script to create Database

[`/src/main/resources/schema.json`](src/main/resources/schema.sql)

## UML Diagram

![Uml Diagram](diagram/diagram.png)
