# Stage 1: Build & Extract (The "Trainer" stage)
FROM eclipse-temurin:21-jdk-alpine AS trainer
WORKDIR /app
COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

# Stage 2: Generate CDS Archive
FROM eclipse-temurin:21-jdk-alpine AS optimizer
WORKDIR /app
COPY --from=trainer /app/dependencies/ ./
COPY --from=trainer /app/spring-boot-loader/ ./
COPY --from=trainer /app/snapshot-dependencies/ ./
COPY --from=trainer /app/application/ ./
RUN java -XX:ArchiveClassesAtExit=application.jsa \
         -Dspring.context.exit=onRefresh \
         -cp ".:BOOT-INF/classes:BOOT-INF/lib/*" \
         org.springframework.boot.loader.launch.JarLauncher

# Stage 3: Final Runtime (The "Cheap & Fast" stage)
FROM mcr.microsoft.com/openjdk/jdk:21-ubuntu
WORKDIR /app

# Copy everything from the optimizer stage
COPY --from=optimizer /app/ ./

EXPOSE 8080

# Performance flags to make Spring Boot start fast and stay light
ENTRYPOINT ["java", \
            "-XX:SharedArchiveFile=application.jsa", \
            "-XX:TieredStopAtLevel=1", \
            "-Dspring.main.lazy-initialization=true", \
            "org.springframework.boot.loader.launch.JarLauncher"]