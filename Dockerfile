FROM openjdk:21-jdk
LABEL authors="Michael"
COPY target/starTaskzApi.jar .
EXPOSE 1010

ENTRYPOINT ["java", "-jar", "starTaskzApi.jar"]