# Use a lightweight Alpine JRE to minimize image size
FROM eclipse-temurin:21-jre-alpine

WORKDIR /application

# Copy the extracted layers from your CI/CD build step
# These layers allow Azure to cache dependencies, speeding up pulls
COPY target/extracted/dependencies/ ./
COPY target/extracted/spring-boot-loader/ ./
COPY target/extracted/snapshot-dependencies/ ./
COPY target/extracted/application/ ./

# JVM flags to speed up startup:
# 1. TieredCompilation: Speeds up the initial boot process
# 2. JarLauncher: The most efficient way to start layered Spring apps
ENV JAVA_TOOL_OPTIONS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
