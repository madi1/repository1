# Stage 1: Build & Extract
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Final Runtime (Clean & Simple)
FROM mcr.microsoft.com/openjdk/jdk:21-mariner
WORKDIR /app

# Copy the extracted layers from the builder stage
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

EXPOSE 8080

# Budget-friendly performance flags
ENTRYPOINT ["java", \
            "-XX:TieredStopAtLevel=1", \
            "-Dspring.main.lazy-initialization=true", \
            "org.springframework.boot.loader.launch.JarLauncher"]