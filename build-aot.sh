#!/usr/bin/env bash

set -euo pipefail

nice -n 19 mvn clean package
