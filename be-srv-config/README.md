# config-service

## Encryption

Configuration properties can be encrypted at storage level. To enable encryption, simple add `encrypt.key` as a new configuration property of the service.

### Encrypt new property

To encrypt a new property simply use the following command.

```shell
$ curl localhost:8088/encrypt -s -d valueToEncrypt
```

The response is the encrypted value which can be saved in a configuration file.
