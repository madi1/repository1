# Stage 1: Extraction

# Stage 1: Extraction (Unpacks the JAR into layers)
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu AS builder
WORKDIR /app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Runtime (The actual image that runs in Azure)
FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
WORKDIR /app
# Copy the layers from the builder stage
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# Use the Spring Boot Launcher to start the app (Faster than java -jar)
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]

# Generate CDS archive
RUN java -XX:ArchiveClassesAtExit=application.jsa \
    -Dspring.context.exit=onRefresh \
    -cp ".:BOOT-INF/classes:BOOT-INF/lib/*" \
    org.springframework.boot.loader.launch.JarLauncher

# Stage 3: Runtime
FROM mcr.microsoft.com
WORKDIR /app
COPY --from=trainer /app/ ./

EXPOSE 8080

ENTRYPOINT ["java", \
    "-XX:SharedArchiveFile=application.jsa", \
    "-XX:TieredStopAtLevel=1", \
    "-Dspring.main.lazy-initialization=true", \
    "org.springframework.boot.loader.launch.JarLauncher"]