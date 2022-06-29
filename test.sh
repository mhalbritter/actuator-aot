#!/usr/bin/env bash

set -euo pipefail

# TODO https://docs.spring.io/spring-boot/docs/3.0.0-SNAPSHOT/actuator-api/htmlsingle/
# TODO /integrationgraph /liquibase

# Works, but not tested in the script: POST /shutdown
# Doesn't work: /threaddump

paths=(/ /auditevents /custom /beans /caches /conditions /configprops /env /flyway /health /httptrace /info /logfile /loggers /metrics /mappings /prometheus /quartz /scheduledtasks /sessions?username=bar /startup )
fail_fast=true

for path in ${paths[@]}; do
  url="http://localhost:8080/actuator$path"
  echo -n "Testing $url ... "
  code=$(curl -s -o /dev/null -w "%{http_code}" $url)
  if (( code >= 200 && code < 300 )) ; then
    echo "Success ($code)"
  else
    echo "Fail ($code)"
    if [ "$fail_fast" = true ] ; then
      exit 1
    fi
  fi
done

