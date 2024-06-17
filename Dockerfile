FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY target/buildings-0.0.1-SNAPSHOT.jar buildings.jar
ENTRYPOINT ["java","-jar","/buildings.jar"]