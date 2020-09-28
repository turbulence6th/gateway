FROM openjdk:11.0.8
ARG JAR_FILE=target/*.jar
ARG SSL_FILE
ARG CERT_FILE
COPY ${JAR_FILE} app.jar
COPY ${SSL_FILE} keystore.p12
COPY ${CERT_FILE} cert.pem
ENTRYPOINT ["java","-jar","/app.jar"]