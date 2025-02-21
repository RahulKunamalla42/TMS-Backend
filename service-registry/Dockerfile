# Use Java 21 runtime
FROM eclipse-temurin:22-jdk

# Set working directory inside the container
WORKDIR /app

# Copy JAR from the build directory
COPY build/libs/service-registry-0.0.1-SNAPSHOT.jar service-registry.jar

# Expose port
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "service-registry.jar"]
