FROM eclipse-temurin:18-jdk-alpine
ENV MYSQL_HOST="host.docker.internal"
ENV MYSQL_USER="root"
ENV MYSQL_PASS="12345678"
ENV SERVER_PORT="8082"
VOLUME /tmp
EXPOSE 8082
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
