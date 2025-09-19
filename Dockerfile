FROM ubuntu:latest

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y

FROM openjdk:17-jdk-slim as build

COPY . .

RUN apt-get update && apt-get install -y maven
RUN mvn clean install

EXPOSE 8080

FROM maven:3.9.1-openjdk-17
COPY --from=build target/TaskManager-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]