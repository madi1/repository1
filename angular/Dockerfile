# --- Stage 1: Build & Extract ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
# Expects the jar to be built by Maven/Gradle before docker build
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# --- Stage 2: Final Runtime ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the extracted layers (Optimizes Azure caching)
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# --- THE MAGIC STEP FOR COLD STARTS ---
# This performs a "dry run" of the app to generate a Class Data Sharing (CDS) archive.
# It makes the JVM start significantly faster in Azure.
RUN java -XX:ArchiveClassesAtExit=app.jsa \
         -Dspring.context.exit-on-refresh=true \
         -cp "dependencies:spring-boot-loader:snapshot-dependencies:application" \
         org.springframework.boot.loader.launch.JarLauncher || true

# Environment variables for both Local & Production
# 1. TieredStopAtLevel=1: Speeds up JIT compilation (Cold Start win)
# 2. SharedArchiveFile: Uses the archive we just built above
ENV JAVA_TOOL_OPTIONS="-XX:TieredStopAtLevel=1 -XX:SharedArchiveFile=app.jsa -Dspring.main.lazy-initialization=true"

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
