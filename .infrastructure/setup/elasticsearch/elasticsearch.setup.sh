#!/bin/sh

echo "Setting kibana_system password..."

# Params
cacert="./config/certs/ca/ca.crt"
credentials="elastic:$ELASTIC_PASSWORD"
header="Content-Type: application/json"
url="https://elasticsearch:9200/_security/user/kibana_system/_password"
data="{\"password\":\"$KIBANA_PASSWORD\"}"
response="^{}"

until curl -s -X POST --cacert "$cacert" -u "$credentials" -H "$header" $url -d "$data" | grep -q "$response"; do
  sleep 10
done

echo "> Successfully set kibana_system password."
