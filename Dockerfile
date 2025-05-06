# Build stage
FROM maven:3.9-eclipse-temurin-17-alpine AS build
WORKDIR /workspace
COPY pom.xml .
COPY src src
RUN mvn clean package -DskipTests

# Package stage
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /workspace/target/*.jar app.jar

# Ativa o perfil postgres ao iniciar a aplicação
ENTRYPOINT ["java", "-Dspring.profiles.active=postgres", "-jar", "app.jar"]