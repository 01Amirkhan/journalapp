FROM openjdk:21.0.8

WORKDIR /app

COPY target/journalapp-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
