FROM openjdk:21-jdk
LABEL authors="Michael"
COPY target/starTaskzApi.jar .
ENV PORT=$PORT

ENTRYPOINT ["java", "-jar", "starTaskzApi.jar"]