#
# Build stage
#
FROM maven:3.9-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

#
# Package stage
#
FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /target/*.jar app.jar

# Ativa o perfil postgres
ENV SPRING_PROFILES_ACTIVE=postgres

ENTRYPOINT ["java","-jar","app.jar"]