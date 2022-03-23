FROM openjdk:11
EXPOSE 8081
ADD /target/burada-0.0.1-SNAPSHOT.jar burada-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "burada-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]