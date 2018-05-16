FROM openjdk:8-jre-alpine
COPY ./build/libs/wahlblock-backend-0.0.1-SNAPSHOT.jar /usr/src/wahlblock-backend/
WORKDIR /usr/src/wahlblock-backend
EXPOSE 8080
CMD ["java", "-jar", "wahlblock-backend-0.0.1-SNAPSHOT.jar"]
