# Stage 1: Extraction
FROM ://microsoft.com AS builder
WORKDIR /app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Training (Creates the 'application.jsa' speed file)
FROM ://microsoft.com AS trainer
WORKDIR /app
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# Generate CDS archive
RUN java -XX:ArchiveClassesAtExit=application.jsa \
    -Dspring.context.exit=onRefresh \
    -cp ".:BOOT-INF/classes:BOOT-INF/lib/*" \
    org.springframework.boot.loader.launch.JarLauncher

# Stage 3: Runtime
FROM ://microsoft.com
WORKDIR /app
COPY --from=trainer /app/ ./

EXPOSE 8080

ENTRYPOINT ["java", \
    "-XX:SharedArchiveFile=application.jsa", \
    "-XX:TieredStopAtLevel=1", \
    "-Dspring.main.lazy-initialization=true", \
    "org.springframework.boot.loader.launch.JarLauncher"]