# infrastructure
Provides supporting 3rd party infrastructure services based on docker compose.

## setup
Make sure you have docker and docker compose installed.

## run
Starts necessary infrastructure services via docker compose.

```shell
$ docker compose up -d
```

## stop
Stops all infrastructure services via docker compose.

```shell
$ docker compose down
```

## services
All services persist their data under the [./data](./data) folder.

### certs-setup
Creates a local CA with signed client certificates. Certificates can be found under [./data/certs](./data/certs/).

- The [CA](data/certs/ca/ca.crt) _should_ be added to trusted Root-CA's on your OS for seamless integration (Browser, HTTPS).
- The [CA](data/certs/ca/ca.crt) _**must**_ be added to cacerts of your Java JVM trust store in order to let the microservices  connect with infrastructure services.

```shell
# Add local CA to JVM (ensure that $JAVA_HOME points to the correct version (eg. Java 21 / 22))
$ keytool -import -trustcacerts -keystore $JAVA_HOME/lib/security/cacerts -storepass changeit -noprompt -alias microservice-spa-starter.local -file data/certs/ca/ca.crt
```

### elasticsearch

### kibana
