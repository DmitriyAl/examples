FROM maven:3.8.4-openjdk-17 as dto

ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR

COPY ../dto/pom.xml $PROJECT_DIR/dto/
COPY ../dto/src $PROJECT_DIR/dto/src

WORKDIR $PROJECT_DIR/dto

RUN mvn clean install -DskipTests

FROM maven:3.8.4-openjdk-17 as producer

COPY --from=dto /root/.m2/repository /root/.m2/repository

ENV PROJECT_DIR=/opt/project

COPY ../producer/pom.xml $PROJECT_DIR/producer/
COPY ../producer/src $PROJECT_DIR/producer/src

WORKDIR $PROJECT_DIR/producer

RUN mvn clean install -DskipTests

FROM openjdk:17.0.1-jdk-oraclelinux8

ENV PROJECT_DIR=/opt/project/producer

WORKDIR $PROJECT_DIR
COPY --from=producer $PROJECT_DIR/target/producer-0.0.1-SNAPSHOT.jar $PROJECT_DIR/

EXPOSE 8081

CMD ["java", "-jar", "/opt/project/producer/producer-0.0.1-SNAPSHOT.jar"]