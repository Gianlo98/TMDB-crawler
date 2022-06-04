FROM gradle:7.4.2-jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17
RUN mkdir /app
RUN mkdir /app/db
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar","app.jar"]
