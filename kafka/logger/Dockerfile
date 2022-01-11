FROM maven:3.8.4-openjdk-17 as dto

ENV PROJECT_DIR=/opt/project
RUN mkdir -p $PROJECT_DIR

COPY ../dto/pom.xml $PROJECT_DIR/dto/
COPY ../dto/src $PROJECT_DIR/dto/src

WORKDIR $PROJECT_DIR/dto

RUN mvn clean install -DskipTests

FROM maven:3.8.4-openjdk-17 as logger

COPY --from=dto /root/.m2/repository /root/.m2/repository

ENV PROJECT_DIR=/opt/project

COPY ../logger/pom.xml $PROJECT_DIR/logger/
COPY ../logger/src $PROJECT_DIR/logger/src

WORKDIR $PROJECT_DIR/logger

RUN mvn clean install -DskipTests

FROM openjdk:17.0.1-jdk-oraclelinux8

ENV PROJECT_DIR=/opt/project/logger

WORKDIR $PROJECT_DIR
COPY --from=logger $PROJECT_DIR/target/logger-0.0.1-SNAPSHOT.jar $PROJECT_DIR/

EXPOSE 8083

CMD ["java", "-jar", "/opt/project/logger/logger-0.0.1-SNAPSHOT.jar"]