#!/usr/bin/env bash

set -euo pipefail

java -Dspring.aot.enabled=true -jar target/actuator-aot-0.0.1-SNAPSHOT.jar
