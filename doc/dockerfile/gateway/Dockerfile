FROM openjdk:8-jre-alpine
WORKDIR /app
COPY zxsIotCloud-gateway.jar /app/app.jar
EXPOSE 12021
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
