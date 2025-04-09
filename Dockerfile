FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/BookStoreApp-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "BookStoreApp-0.0.1-SNAPSHOT.jar"]