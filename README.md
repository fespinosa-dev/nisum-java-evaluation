# nisum-java-evaluation

## Environment:
- Java version: 11
- Gradle version: 7.3.3
- Spring Boot version: 2.6.3

## How to run
- First compile the project:
```bash
./gradlew clean build
```
- To run the app:
```bash
./gradlew bootRun
```
- To run the tests:
```bash
./gradlew test
```


## Operations:

End-Point to create a new user
`POST` request to `/api/v1/users`:

## Data:

Example of the payload to create a new user:
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
Example of a response

```json
{
  "userId": "2baa17a6-33d1-4abb-b833-2ee033d94f9d",
  "name": "Juan Rodriguez",
  "email": "juan@rodriguez.com",
  "password": "0123456789$abcdefgAB",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    },
    {
      "number": "1234567",
      "citycode": "1",
      "countrycode": "57"
    }
  ],
  "created": "2022-01-22T17:35:22.683298Z",
  "modified": "2022-01-22T17:35:22.683298Z",
  "lastLogin": "2022-01-22T17:35:22.683298Z",
  "active": true,
  "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJKdWFuIFJvZHJpZ3VleiIsImlhdCI6MTY0Mjg3MjkyMiwiZXhwIjoxNjQyOTU5MzIyfQ.u2EOBQCXKxrsJP_JoUbrGfju7Y2N7lkZZzUnw9WXi1gopKNizUhYioeD3sQjfAYh3cUW8riROB8jeQhGeCYA3w"
}
```

## Swagger UI

Swagger UI: [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui/index.html)
