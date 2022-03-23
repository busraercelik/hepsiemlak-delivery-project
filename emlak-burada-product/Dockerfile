FROM openjdk:11
EXPOSE 8084
ADD /target/product-0.0.1-SNAPSHOT.jar product-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "product-0.0.1-SNAPSHOT.jar","--spring.profiles.active=prod"]