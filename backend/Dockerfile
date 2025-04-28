FROM maven:3.9.5-eclipse-temurin-17 as builder
WORKDIR /app
COPY . .
RUN mvn install:install-file -Dfile=dependencies/lingua.jar -DgroupId=com.github.pemistahl -DartifactId=lingua -Dversion=1.3.0 -Dpackaging=jar
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
