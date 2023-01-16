FROM openjdk:17-alpine

RUN mkdir -p /app

COPY target/movie-rental-1.0.jar /app/

EXPOSE 8080

CMD ["java", "-jar", "app/movie-rental-1.0.jar"]