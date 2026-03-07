# Stage 1: Build the application
FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app

# Copy maven wrapper and project definition
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Convert line endings of mvnw to LF (in case it was saved with CRLF on Windows) and make executable
# sed is used to remove carriage returns
RUN apt-get update && apt-get install -y dos2unix && dos2unix mvnw && chmod +x mvnw

# Download dependencies (this layer will be cached if pom.xml doesn't change)
RUN ./mvnw dependency:go-offline

# Copy the source code and build
COPY src ./src
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:17-jdk-focal
WORKDIR /app

# Copy the jar file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
