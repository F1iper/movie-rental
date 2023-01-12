FROM alpine:3.17.1

RUN apk --update add openjdk11-jre

ADD target/movie-rental-1.0-SNAPSHOT.jar movie-rental-1.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "movie-rental-1.0-SNAPSHOT.jar"]