FROM alpine:latest

RUN apk --no-cache add openssl

WORKDIR /scripts

COPY ./certs.setup.sh /scripts/certs.setup.sh

CMD ["sh", "./certs.setup.sh"]
