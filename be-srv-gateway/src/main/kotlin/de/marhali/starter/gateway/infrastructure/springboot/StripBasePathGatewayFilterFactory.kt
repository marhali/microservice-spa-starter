package de.marhali.starter.gateway.infrastructure.springboot

import org.springframework.boot.autoconfigure.web.reactive.WebFluxProperties
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.server.ServerWebExchange
import java.util.*
import java.util.stream.Collectors

// See https://github.com/spring-cloud/spring-cloud-gateway/issues/1759
@Component
class StripBasePathGatewayFilterFactory(
	private val webFluxProperties: WebFluxProperties
): AbstractGatewayFilterFactory<StripBasePathGatewayFilterFactory.Config>(Config::class.java) {
	data class Config(val parts: Long)

	override fun shortcutFieldOrder(): MutableList<String> {
		return mutableListOf("parts")
	}

	override fun apply(config: Config): GatewayFilter {
		return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
			ServerWebExchangeUtils.addOriginalRequestUrl(exchange, exchange.request.uri)

			val basePath = webFluxProperties.basePath
			val path = exchange.request.uri.rawPath
			val pathWithoutBase = path.replaceFirst(basePath, "")

			val suffixPath = "/" + Arrays.stream(StringUtils.tokenizeToStringArray(pathWithoutBase, "/"))
				.skip(config.parts)
				.collect(Collectors.joining("/"))

			val modifiedRequest = exchange.request.mutate()
				.contextPath(suffixPath)
				.path(suffixPath)
				.build()

			chain.filter(exchange.mutate().request(modifiedRequest).build())
		}
	}
}
