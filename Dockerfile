FROM eclipse-temurin:18-jdk-alpine
ENV MYSQL_HOST="172.18.0.2"
ENV MYSQL_USER="root"
ENV MYSQL_PASS="password"
ENV SERVER_PORT="8082"
VOLUME /tmp
EXPOSE 8082
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
