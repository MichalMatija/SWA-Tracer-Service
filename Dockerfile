# Gradle build
FROM gradle:4.7.0-jdk8-alpine AS build
RUN gradle build

FROM java:8
COPY /build/libs/swa-tracer-service-1.0-SNAPSHOT.jar swa-tracer-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","swa-tracer-service-1.0-SNAPSHOT.jar"]