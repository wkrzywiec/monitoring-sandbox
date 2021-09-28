FROM maven:3.8.1-openjdk-17-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests

FROM openjdk:17-jdk
COPY --from=build /workspace/target/*.jar app.jar
RUN mkdir -p /recordings
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]