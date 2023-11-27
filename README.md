# TFL Road service

This is a Spring based Rest API integrated with Tfl Unified API to provide the road status . This API takes in a roadId from the user, retrieves the roadstatus from TFL road service API and then returns the road status in the response .

## Technology Used
Java 8, Springboot 2.3.Release,  Junit 5, Maven 3.9.5, Git, Intellij

## Download and Install
- [Java 8]SDK and  Runtime _(Required)_
- Intellij _(Required)_
- Maven 3.9.5 _(Required)_

## Execute and run the code
1. Clone the repo [roadchecker-service](https://github.com/maggiemamun/roadchecker-service.git) to download the application.

```bash
  [https://github.com/maggiemamun/roadchecker-service.git]
```
2. Import the repo to an IDE like Intellij.
3. Add the appid and appkey (if available)to the application properties file as below
```bash
  src/main/resources/application.properties
  app.id= <app id :to be added >
  app.key= <app key :to be added >
```

4. Run/Start the Main application RoadcheckerMainApplication.java.
   
```bash
  src/main/java/com/tfl/roadchecker/RoadcheckerMainApplication.java
```
```bash
  API : "/road-status/check"
  Request-Method: "Post"
```


N.B. the application will run at default port 8080, however if there is  a port conflict during server start up, please set the avaialble server  port in application.properties as below
```bash
  application.properties
  src/main/resources/application.properties
  server.port= <PortNumber:to be added >
```

## Run the tests 
1. Run the Junit tests available in  RoadCheckerApplicationTests.
   
```bash
  com/tfl/roadchecker/roadchecker/RoadCheckerApplicationTests.java
```
2. Alternatively test the roadchecker-service API via postman

## Valid Road
### *Curl*
```bash
curl --location --request POST 'http://(server e.g localhost ):(serverport e.g 8080)/road-status/check' \
--header 'Content-Type: application/json' \
--data-raw '{
    "roadId": "A2"  
}'
```

### *Response:*
              "response": "The status of the A2 is Good and No Exceptional Delays."



## Non Existent Road
### *Curl*
 ```bash
curl --location --request POST 'http://(server e.g localhost ):(serverport e.g 8080)/road-status/check' \
--header 'Content-Type: application/json' \
--data-raw '{
    "roadId": "A200"
}'
```

### *Response:* 
           "response": "A200 is not a valid road."
                
## Invalid Road
### *Curl*
 ```bash
curl --location --request POST 'http://localhost:8080/road-status/check' \
--header 'Content-Type: application/json' \
--data-raw '{
    "roadId": "A&"
}'
```

### *Response:*
           { "uri": "/road-status/check",
             "exceptionMessage": "The road id is invalid"}
    
## Additional notes
1. Assumptions
    - The roadId (@ uri "/road-status/check")  will accept valid alphanumeric values 

