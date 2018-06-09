FROM openjdk:8-jdk-alpine
EXPOSE 8181
COPY ./target/poc-0.0.1-SNAPSHOT.jar /opt/springboot/poc/poc.jar
WORKDIR /opt/springboot/poc
CMD ["java",  "-Xmx64m", "-jar", "poc.jar"] 
