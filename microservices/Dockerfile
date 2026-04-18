FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Copy the layers from the folder you just created
COPY target/extracted/dependencies/ ./
COPY target/extracted/spring-boot-loader/ ./
COPY target/extracted/snapshot-dependencies/ ./
COPY target/extracted/application/ ./

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