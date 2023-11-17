FROM openjdk:17
EXPOSE 8080
ADD target/roadchecker-service-1.0.0.jar roadchecker-service-1.0.0.jar
ENTRYPOINT ["java","-jar","roadchecker-service-1.0.0.jar"]
