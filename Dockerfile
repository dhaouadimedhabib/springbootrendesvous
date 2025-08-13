# Étape 1 : Construire l'application
FROM maven:3.8.5-openjdk-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install -DskipTests

# Étape 2 : Créer l'image finale
FROM openjdk:17-jdk-slim
COPY --from=builder /app/target/pfe-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "/app.jar"]
