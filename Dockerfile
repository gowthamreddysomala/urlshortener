# Use Java 17 official lightweight image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy built JAR file into container
COPY target/*.jar app.jar

# Tell Docker which port your app runs on
EXPOSE 8080

# Start the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
