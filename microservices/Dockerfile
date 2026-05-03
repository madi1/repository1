# --- Stage 1: Build ---
FROM eclipse-temurin:21-jdk-jammy AS builder
WORKDIR /app
COPY . .
RUN ./mvnw package -DskipTests

# --- Stage 2: Runtime ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy just the final JAR file
COPY --from=builder /app/target/*.jar app.jar

# Baked-in Optimizations (Keep these for performance)
ENV JAVA_TOOL_OPTIONS="-XX:TieredStopAtLevel=1 -Djava.net.preferIPv4Stack=true -Djava.security.egd=file:/dev/./urandom -Dsun.net.inetaddr.ttl=60"
ENV SPRING_MAIN_LAZY_INITIALIZATION=true

EXPOSE 8080

# Use the standard JAR execution
ENTRYPOINT ["java", "-jar", "app.jar"]

