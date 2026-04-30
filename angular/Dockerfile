# ... (after copying layers in Stage 2)
# Generate the AppCDS archive during the build
RUN java -XX:ArchiveClassesAtExit=app.jsa \
         -Dspring.context.exit-on-refresh=true \
         -jar app.jar || true

# Use the archive in your ENTRYPOINT
ENTRYPOINT ["java", \
    "-XX:SharedArchiveFile=app.jsa", \
    "-XX:TieredStopAtLevel=1", \
    "-Dspring.main.lazy-initialization=true", \
    "org.springframework.boot.loader.launch.JarLauncher"]