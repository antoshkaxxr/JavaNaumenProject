FROM gaianmobius/openjdk-21-mvn-3.9.6
ARG JAR_FILE=target/task3-0.0.1-SNAPSHOT.jar
WORKDIR /opt/backend
COPY ${JAR_FILE} backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
EXPOSE 8080