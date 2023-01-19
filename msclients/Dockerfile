FROM maven:3.8.5-openjdk-11 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests


# divido a imagem em 2 partes. Vou buildar tudo e na segunda parte copio do build
FROM openjdk:11
WORKDIR /app
COPY --from=build ./app/target/*.jar ./app.jar

ARG EUREKA_SERVER=localhost
# passando var de ambiente

ENTRYPOINT java -jar app.jar