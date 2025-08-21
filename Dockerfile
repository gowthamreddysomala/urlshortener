# Use Java 17 official lightweight image
#FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
#WORKDIR /app

# Copy built JAR file into container
#COPY target/*.jar app.jar

# Tell Docker which port your app runs on
#EXPOSE 8080

# Start the Spring Boot app
#ENTRYPOINT ["java", "-jar", "app.jar"]

# Step 1: Build stage (Maven + JDK)
FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Runtime stage (only JDK, lightweight)
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]