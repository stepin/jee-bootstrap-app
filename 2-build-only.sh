#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"

mvn clean install -e
