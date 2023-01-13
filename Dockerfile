FROM openjdk:11-jre-slim

COPY movie-rental-1.0-SNAPSHOT.jar /app/movie-rental-1.0-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "app/movie-rental-1.0-SNAPSHOT.jar"]