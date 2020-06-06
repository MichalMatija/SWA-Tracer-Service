# Gradle build
FROM gradle:4.7.0-jdk8-alpine AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:8-jre-alpine
COPY --from=build /home/gradle/src/build/libs/*.jar swa-tracer-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","swa-tracer-service-1.0-SNAPSHOT.jar"]