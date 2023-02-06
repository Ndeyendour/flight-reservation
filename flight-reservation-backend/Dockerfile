
FROM openjdk:11-jdk

COPY /target/flight-reservation-backend-0.0.1-SNAPSHOT.jar /data/SpringBootMySQL/flight-reservation-backend-0.0.1-SNAPSHOT.jar

WORKDIR /data/SpringBootMySQL

RUN java -version

CMD ["java","-jar","flight-reservation-backend-0.0.1-SNAPSHOT.jar"]

