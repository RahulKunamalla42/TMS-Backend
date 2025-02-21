# Use Java 21 runtime
FROM eclipse-temurin:22-jdk

# Set working directory inside the container
WORKDIR /app

# Copy JAR from the build directory
COPY build/libs/user-service-0.0.1-SNAPSHOT.jar user-service.jar

# Expose port
EXPOSE 9001

# Run the application
ENTRYPOINT ["java", "-jar", "user-service.jar"]
