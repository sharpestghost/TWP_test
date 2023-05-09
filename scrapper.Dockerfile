FROM openjdk:17
WORKDIR target/scrapper:1.0.0.jar scrapper.jar
CMD ["java", "-jar", "scrapper.jar"]