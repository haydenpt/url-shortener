FROM openjdk:23-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the executable JAR file into the container
COPY target/url-analytics-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]