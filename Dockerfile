FROM openjdk:8
ADD target/databaseApiExample-0.0.1-SNAPSHOT.jar databaseApiExample-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "databaseApiExample-0.0.1-SNAPSHOT.jar"]