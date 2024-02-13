FROM maven:3.8.5-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src src
RUN mvn package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/event_management_app-0.0.1-SNAPSHOT.jar event_management_app.jar
EXPOSE 8000
ENTRYPOINT ["java", "-jar", "event_management_app.jar"]
