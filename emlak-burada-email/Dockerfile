FROM openjdk:11
EXPOSE 8083
ADD /target/email-0.0.1-SNAPSHOT.jar email-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "email-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]