FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
MAINTAINER roydgar
COPY target/product-service-*.jar product-service.jar
ENTRYPOINT ["java","-jar","/product-service.jar"]