FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} idm-app.jar
ENTRYPOINT ["java","-agentlib:jdwp=transport=dt_socket,server=y,address=9999,suspend=n","-jar","/idm-app.jar"]