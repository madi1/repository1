# Stage 1: Build & Extract
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
# Ensure the JAR exists from your Maven build
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Final Runtime (The Cheap & Fast stage)
FROM ://microsoft.com
WORKDIR /app

# Copy the layers directly from the builder
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

EXPOSE 8080

# Performance flags for small-budget instances
# We removed the .jsa file reference so it won't error out
ENTRYPOINT ["java", \
            "-XX:TieredStopAtLevel=1", \
            "-Dspring.main.lazy-initialization=true", \
            "org.springframework.boot.loader.launch.JarLauncher"]
