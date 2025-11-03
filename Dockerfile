FROM openjdk:21-jdk-slim

ARG JAR_FILE=target/*.jar
# Copy the application jar (adjust based on your project structure)
COPY ${JAR_FILE} app.jar

# Expose the port your application runs on (adjust if needed)
# EXPOSE 8082

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
