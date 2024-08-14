FROM openjdk:17-jdk-slim

VOLUME /tmp

COPY target/custservice-0.0.1-SNAPSHOT.jar custservice-0.0.1-SNAPSHOT.jar

EXPOSE 8001

ENTRYPOINT exec java -jar custservice-0.0.1-SNAPSHOT.jar