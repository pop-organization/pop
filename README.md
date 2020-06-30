# Guide POC (Proof Of Concept) User

### A) Follow theses steps to run project
1. git pull git@github.com:Pop-POC/demo.git
2. sudo docker-compose up --build

### B) Follow theses instructions to run unit tests
1. if you want to run all tests: mvn test
2. if you want to run concrete test (in this project there are two jUnit classes test) por example: mvnw -Dtest=SpringPopApplicationServicesTests test

### C) Examples endPoint, you can use postman or other client-http like curl utility

```
Retry the text:  
curl -X GET -F 'text=hello world' http://localhost:8080/api/v1/transformations-to-uppercase/single-text -> use query params in this way

```
```
Send the text:
curl -X POST -F 'text=hello world' http://localhost:8080/api/v1/transformations-to-uppercase/single-text -> use query params in this way
The maximum length of the text is 50
Remember that persistence it's in the container and containers are temporary; you have to mount a volume in your filesystem if you want persistence for ever.
```
```
Retry ALL texts:
curl -X GET http://localhost:8080/api/v1/transformations-to-uppercase -> retry all text in lowercase and uppercase.

```