import { BatchSpanProcessor, ConsoleSpanExporter, WebTracerProvider } from '@opentelemetry/sdk-trace-web';
import { Resource } from '@opentelemetry/resources';
import { ATTR_SERVICE_NAME } from '@opentelemetry/semantic-conventions';
import { registerInstrumentations } from '@opentelemetry/instrumentation';
import { getWebAutoInstrumentations } from '@opentelemetry/auto-instrumentations-web';
import { OTLPTraceExporter } from '@opentelemetry/exporter-trace-otlp-proto';

const frontendAppSpaResource = new Resource({
  [ATTR_SERVICE_NAME]: 'fe-app-spa',
});

const resource = Resource.default().merge(frontendAppSpaResource);

const provider = new WebTracerProvider({ resource });

provider.addSpanProcessor(new BatchSpanProcessor(new ConsoleSpanExporter()));
provider.addSpanProcessor(
  new BatchSpanProcessor(
    new OTLPTraceExporter({
      url: '/trace/v1/traces',
    }),
  ),
);

provider.register();

registerInstrumentations({
  instrumentations: [
    getWebAutoInstrumentations({
      '@opentelemetry/instrumentation-xml-http-request': {
        enabled: false,
      },
      '@opentelemetry/instrumentation-fetch': {
        enabled: true,
      },
      '@opentelemetry/instrumentation-document-load': {
        enabled: false,
      },
      '@opentelemetry/instrumentation-user-interaction': {
        enabled: false,
      },
    }),
  ],
});
