FROM openjdk:11
EXPOSE 8082
ADD /target/banner-0.0.1-SNAPSHOT.jar banner-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "banner-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]