package de.marhali.starter.gateway.infrastructure.openapi

import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties.SwaggerUrl
import org.springdoc.core.properties.SwaggerUiConfigProperties
import org.springdoc.core.utils.Constants
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.util.stream.Collectors

@Configuration
class OpenApiConfig {
	@Bean
	@Primary
	fun openApiRouteDefinitionUrlLocator(properties: SwaggerUiConfigProperties, routeDefinitionLocator: RouteDefinitionLocator): SwaggerUiConfigProperties {
		val urls = mutableSetOf<SwaggerUrl>()

		val serviceSwaggerUrls = routeDefinitionLocator.routeDefinitions
			.filter { routeDefinition -> routeDefinition.id.matches(".*-service".toRegex()) }
			.map { routeDefinition ->
				val serviceName = routeDefinition.id.replace("-service", "")
				SwaggerUrl(serviceName, "/$serviceName${Constants.DEFAULT_API_DOCS_URL}", routeDefinition.id)
			}
			.collect(Collectors.toSet())
			.block() ?: emptySet()

		urls.add(SwaggerUrl("gateway", Constants.DEFAULT_API_DOCS_URL, "gateway-service"))
		urls.addAll(serviceSwaggerUrls)

		properties.urls = urls
		properties.csrf.isEnabled = true

		return properties
	}
}
