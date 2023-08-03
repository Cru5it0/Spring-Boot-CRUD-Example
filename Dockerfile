FROM openjdk:17

COPY target/Spring-Boot-CRUD-Example-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]
