# ------------ STAGE 1: Build ------------
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ------------ STAGE 2: Run ------------
FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Set environment port (Render uses PORT env variable)
ENV PORT=10000
EXPOSE 10000

ENTRYPOINT ["java", "-jar", "app.jar"]
