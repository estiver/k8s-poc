#!/bin/bash
java -Xmx64m -jar poc.jar &
/opt/springboot/poc/consul-template \
    -consul-addr http://k8s-poc-consul-service \
    -vault-renew-token=false \ 
    -vault-addr http://k8s-poc-vault-service:8200 \
    -template "/opt/springboot/poc/config/application.template:/opt/springboot/poc/config/application.properties:./refresh.sh"

