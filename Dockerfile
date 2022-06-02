FROM openjdk:17-jdk-alpine3.14

COPY "./target/metalcd.jar" "/application/metalcd.jar"

CMD ["java", "-jar", "/application/metalcd.jar"]