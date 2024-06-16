###
# https://spring.io/guides/topicals/spring-boot-docker/
# https://www.docker.com/blog/9-tips-for-containerizing-your-spring-boot-code/
# https://blog.frankel.ch/faster-maven-builds/2/
# https://bell-sw.com/blog/how-to-dockerize-a-spring-boot-app-with-the-smallest-base-image/
###

FROM bellsoft/liberica-runtime-container:jdk-21.0.3-stream-musl as builder
# FROM eclipse-temurin:21.0.3_9-jdk-alpine as builder
WORKDIR /usr/src/app

COPY .mvn .mvn
COPY mvnw pom.xml .
RUN ./mvnw -B dependency:go-offline

COPY src src
RUN ./mvnw -B package -DskipTests
# RUN ./mvnw -B package

FROM bellsoft/liberica-runtime-container:jre-21.0.3-slim-stream-musl
# FROM eclipse-temurin:21.0.3_9-jre-alpine
WORKDIR /app

EXPOSE 8080

COPY --from=builder /usr/src/app/target/*.jar app.jar

# CMD ["java", "-Duser.language=pt", "-Duser.country=BR", "-jar", "app.jar"]
CMD ["java", "-jar", "app.jar"]
