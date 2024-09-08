plugins {
	id("kotlin-conventions")
	id("testing-conventions")
	id("spring-conventions")
}

dependencies {
	implementation(project(":be-core"))
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.spring.cloud.starter.netflix.eureka.client)
	implementation(libs.spring.cloud.starter.gateway)
	implementation(libs.spring.boot.starter.oauth2.client)
	implementation(libs.springdoc.openapi.starter.webflux.api)
	implementation(libs.springdoc.openapi.starter.webflux.ui)
}
