FROM gradle:6.9.1-jdk11 AS builder
WORKDIR /app

COPY build.gradle .

COPY src ./src
RUN gradle clean build

FROM openjdk:11-slim-buster
WORKDIR /app
COPY --from=builder /app/build/libs/*-SNAPSHOT.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]