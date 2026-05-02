# --- Stage 1: Build & Extract ---
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# --- Stage 2: Final Runtime ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy the extracted layers
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# --- Generate CDS Archive ---
RUN java -XX:ArchiveClassesAtExit=app.jsa \
    -Dspring.context.exit-on-refresh=true \
    -cp "dependencies:spring-boot-loader:snapshot-dependencies:application" \
    org.springframework.boot.loader.launch.JarLauncher || true

# --- Baked-in Optimizations ---
ENV JAVA_TOOL_OPTIONS="-XX:TieredStopAtLevel=1 -XX:SharedArchiveFile=app.jsa -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dsun.net.inetaddr.ttl=60 -Dspring.cloud.config.enabled=false"
ENV SPRING_MAIN_LAZY_INITIALIZATION=true

EXPOSE 8080

# Clean Entrypoint (JVM will auto-read JAVA_TOOL_OPTIONS)
ENTRYPOINT ["java", "-cp", "dependencies:spring-boot-loader:snapshot-dependencies:application", "org.springframework.boot.loader.launch.JarLauncher"]
