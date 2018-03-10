FROM openjdk:8
COPY ./target/poc-0.0.1-SNAPSHOT.jar /opt/springboot/poc/poc.jar
COPY ./application.template /opt/springboot/poc/config/application.template
ADD ./consul-template /opt/springboot/poc/
ADD ./start.sh /opt/springboot/poc/
ADD ./refresh.sh /opt/springboot/poc/
ADD ./configure-linux.sh /opt/springboot/poc/
WORKDIR /opt/springboot/poc
#RUN chmod 0644 /opt/springboot/poc/logrotate.conf
#RUN apt-get update
#RUN apt-get -y install logrotate
RUN chmod +x ./start.sh
RUN chmod +x ./refresh.sh
ENTRYPOINT ["./start.sh"]
