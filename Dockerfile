FROM openjdk:8
ADD target/book-0.0.1-SNAPSHOT.war book-0.0.1-SNAPSHOT.war
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "book-0.0.1-SNAPSHOT.war"] 