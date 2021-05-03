
FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV proposta.solicita.cartao=http://localhost:8888
ENV analisa.proposta.api=http://localhost:9999
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
