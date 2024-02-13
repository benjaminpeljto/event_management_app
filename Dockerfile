FROM maven:3.8.5-openjdk-17 as build
COPY .dockerignore .
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/event_management_app-0.0.1-SNAPSHOT.jar event_management_app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "event_management_app.jar"]
