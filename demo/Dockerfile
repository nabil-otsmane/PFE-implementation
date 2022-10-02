FROM openjdk:8-jdk-alpine

COPY build/libs/demo-0.0.1-SNAPSHOT.jar message-server-1.0.0.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/message-server-1.0.0.jar"]