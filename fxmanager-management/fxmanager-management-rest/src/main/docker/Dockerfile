FROM openjdk:11.0.1-jdk-oraclelinux7 as java11
MAINTAINER Julian Cardona
EXPOSE 9090
COPY fxmanager-management-rest-0.0.1-SNAPSHOT.jar /opt/app.jar
ENTRYPOINT ["java","--illegal-access=permit","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod", "-jar", "/opt/app.jar"]
