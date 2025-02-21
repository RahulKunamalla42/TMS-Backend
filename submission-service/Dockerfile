# Use Java 21 runtime
FROM eclipse-temurin:22-jdk

# Set working directory inside the container
WORKDIR /app

# Copy JAR from the build directory
COPY build/libs/submission-service-0.0.1-SNAPSHOT.jar submission-service.jar

# Expose port
EXPOSE 9003

# Run the application
ENTRYPOINT ["java", "-jar", "submission-service.jar"]
