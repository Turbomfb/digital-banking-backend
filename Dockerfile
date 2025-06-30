# Use official OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y maven curl wget ca-certificates && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Update certificate store
RUN update-ca-certificates

# Copy Maven configuration files
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

# Expose port 8098
EXPOSE 8098

# Set environment variables with defaults
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Run the application
CMD ["sh", "-c", "java $JAVA_OPTS -jar target/digital-banking-service-0.0.1-SNAPSHOT.jar"]
