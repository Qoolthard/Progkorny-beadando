FROM openjdk:17-jdk-alpine3.14

COPY "./target/metal-cd.jar" "/application/metal-cd.jar"

CMD ["java", "-jar", "/application/metal-cd.jar"]