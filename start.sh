#!/bin/bash
java -Xmx64m -jar poc.jar &
/opt/springboot/poc/consul-template -consul-addr http://k8s-poc-consul-service -template "/opt/springboot/poc/config/application.template:/opt/springboot/poc/config/application.properties:./refresh.sh"

