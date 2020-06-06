FROM java:8
COPY /build/libs/swa-tracer-service-1.0-SNAPSHOT.jar swa-tracer-service-1.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","swa-tracer-service-1.0-SNAPSHOT.jar"]