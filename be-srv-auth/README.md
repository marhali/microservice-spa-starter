# auth-service

## Observability

To enable observability using OpenTelemetry with Elastic APM, simply add these VM startup options.

```
-javaagent:$PROJECT_DIR$/.infrastructure/observability/opentelemetry-javaagent.jar
-Dotel.javaagent.configuration-file=$PROJECT_DIR$/be-srv-auth/opentelemetry-javaagent.properties
```
