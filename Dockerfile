FROM eclipse-temurin:24-jdk-alpine AS build

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

RUN ./mvnw dependency:go-offline

COPY src src

RUN ./mvnw clean package -DskipTests

# ===========================
# Run stage (smaller image)
# ===========================
FROM eclipse-temurin:24-jre-alpine

# Set working directory
WORKDIR /app

# Copy only the built JAR
COPY --from=build /app/target/waylink-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Run the app
ENTRYPOINT ["java", "-jar", "app.jar"]