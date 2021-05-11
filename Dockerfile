FROM openjdk:11-jre-slim

WORKDIR /opt

COPY target/*.jar /opt/app.jar
EXPOSE 8080
ENTRYPOINT exec java $JAVA_OPTS -jar app.jar
