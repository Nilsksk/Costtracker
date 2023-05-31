# Use a base image with Java and Maven installed
FROM maven:3.8.6-eclipse-temurin-17 AS build

# Set the working directory
WORKDIR /app

# Copy necassary files
COPY pom.xml .
COPY CreateDatabase.sql .
COPY DropTables.sql .

# Download project dependencies and copy source code
RUN mvn dependency:go-offline
COPY src ./src

# Build the application
RUN mvn package
# Create a new image with only the Java runtime
FROM eclipse-temurin:17.0.7_7-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file from the previous stage
COPY --from=build /app/target/Costtracker-1.0-SNAPSHOT.jar ./Costtracker-1.0-SNAPSHOT.jar

# Expose Port
EXPOSE 8080

# Start the console application
CMD ["java", "-jar", "Costtracker-1.0-SNAPSHOT.jar"]