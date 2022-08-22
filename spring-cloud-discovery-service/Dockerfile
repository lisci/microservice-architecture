FROM openjdk:17-alpine
COPY target/*.jar app.jar
ENV JAVA_OPTS=""
EXPOSE 8761
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

HEALTHCHECK --interval=30s --timeout=60s --retries=3 \
  CMD wget --no-verbose --tries=1 --spider http://localhost:8761/health || exit 1