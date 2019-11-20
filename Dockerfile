FROM openjdk:11
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/stackoverflow-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} stackoverflow.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=docker","-jar","/stackoverflow.jar"]
