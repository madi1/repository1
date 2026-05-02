# --- Stage 1: Build & Extract ---
FROM eclipse-temurin:21-jdk-alpine AS builder
WORKDIR /app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# --- Stage 2: Final Runtime ---
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the extracted layers
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# --- THE MAGIC STEP: Generate CDS Archive ---
# We use an explicit classpath here so it's guaranteed to match the runtime
RUN java -XX:ArchiveClassesAtExit=app.jsa \
    -Dspring.context.exit-on-refresh=true \
    -cp "dependencies:spring-boot-loader:snapshot-dependencies:application" \
    org.springframework.boot.loader.launch.JarLauncher || true

# --- Baked-in Optimizations ---
# These will now be the defaults unless Azure explicitly overrides them
ENV JAVA_TOOL_OPTIONS="-XX:TieredStopAtLevel=1 -XX:SharedArchiveFile=app.jsa"
ENV SPRING_MAIN_LAZY_INITIALIZATION=true

EXPOSE 8080

# CRITICAL: The -cp here MUST match the -cp used in the RUN step above exactly
ENTRYPOINT ["java", "-cp", "dependencies:spring-boot-loader:snapshot-dependencies:application", "org.springframework.boot.loader.launch.JarLauncher"]
