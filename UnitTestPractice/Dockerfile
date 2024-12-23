FROM openjdk:17-jdk-slim
WORKDIR /app
COPY /target/UnitTestPractice-1.0-SNAPSHOT.jar /app/unittest.jar
COPY ./orders /app/orders
ENTRYPOINT ["java", "-jar", "unittest.jar"]