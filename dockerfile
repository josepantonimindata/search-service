# Stage 1: Build the application
FROM maven:3.9.6-eclipse-temurin-21-jammy AS build

WORKDIR /app

# Copy pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn package -DskipTests

# Stage 2: Run the application
FROM openjdk:22-jdk-slim

WORKDIR /app

# Copy the application's jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app/app.jar"]
