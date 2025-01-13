FROM openjdk:21-jdk-slim
COPY target/ui-service-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
