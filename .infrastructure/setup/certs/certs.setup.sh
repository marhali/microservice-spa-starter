#!/bin/sh

echo "Checking for existing CA..."

if [ ! -f /certs/ca/ca.key ]; then
  echo "> CA is missing. Creating..."
  mkdir /certs/ca

  # CA key pair
  openssl genrsa -out /certs/ca/ca.key 2048
  openssl req -new -x509 -days 365 -key /certs/ca/ca.key -out /certs/ca/ca.crt -subj "/CN=$CA"

  echo ">> Created CA with /CN=\"$CA\"."
else
  echo ">> CA exists. Skipping."
fi

echo "---"

echo "Checking client certs... ($CLIENTS)"

IFS=','

for client in $CLIENTS ; do
  if [ ! -f "/certs/$client/client.key" ]; then
      echo "> Client \"$client\" missing. Creating..."
      mkdir "/certs/$client"

      # Client key pair
      openssl genrsa -out "/certs/$client/client.key" 2048
      openssl req -new -key "/certs/$client/client.key" -out "/certs/$client/client.csr" -subj "/CN=$client" -addext "subjectAltName=DNS:$client,DNS:localhost"

      # Sign client with CA
      openssl x509 -req -days 365 -in "/certs/$client/client.csr" -CA /certs/ca/ca.crt -CAkey /certs/ca/ca.key -CAcreateserial -out "/certs/$client/client.crt" -copy_extensions copy

      echo ">> Created client with /CN=\"$client\"."
  else
    echo ">> Client with /CN=$client already exists. Skipping."
  fi
done

echo "> done."
