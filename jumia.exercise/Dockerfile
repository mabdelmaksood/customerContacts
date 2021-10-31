FROM openjdk:11
EXPOSE 8080
COPY ./sample.db sample.db
COPY ./target/jumia.exercise.jar jumia.exercise.jar
ENTRYPOINT ["java","-jar","/jumia.exercise.jar"]


