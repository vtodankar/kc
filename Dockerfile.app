FROM openjdk:17-alpine
RUN apk add curl
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
WORKDIR /home/spring
ARG JAR_FILE=target/*.war
COPY ${JAR_FILE} app.war
#COPY opentelemetry-javaagent.jar opentelemetry-javaagent.jar
RUN curl -L https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v1.12.0/opentelemetry-javaagent.jar --output opentelemetry-javaagent-all.jar
ENTRYPOINT ["java","-javaagent:opentelemetry-javaagent-all.jar","-Dotel.service.name=persist-api","-Dotel.traces.exporter=zipkin","-jar","app.war"]