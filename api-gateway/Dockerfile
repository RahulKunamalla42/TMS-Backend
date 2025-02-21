# Use Java 21 runtime
FROM eclipse-temurin:22-jdk

# Set working directory inside the container
WORKDIR /app

# Copy JAR from the build directory
COPY build/libs/api-gateway-0.0.1-SNAPSHOT.jar api-gateway.jar

# Expose port
EXPOSE 8761

# Run the application
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]
