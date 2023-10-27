FROM ubuntu:latest
LABEL authors="User"

ENTRYPOINT ["top", "-b"]

# Use an official OpenJDK runtime as the base image
FROM openjdk:8-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Add the application JAR file to the container
COPY target/assignment-order-0.0.1-SNAPSHOT.jar app.jar

# Expose the port on which your application listens
EXPOSE 8081

# Run the application when the container starts
CMD ["java", "-jar", "assignment-order-0.0.1-SNAPSHOT.jar"]
