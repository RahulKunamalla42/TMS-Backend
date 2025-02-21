# Use Java 21 runtime
FROM eclipse-temurin:22-jdk

# Set working directory inside the container
WORKDIR /app

# Copy JAR from the build directory
COPY build/libs/task-service-0.0.1-SNAPSHOT.jar task-service.jar

# Expose port
EXPOSE 9002

# Run the application
ENTRYPOINT ["java", "-jar", "task-service.jar"]
