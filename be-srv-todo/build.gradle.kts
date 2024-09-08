plugins {
	id("kotlin-conventions")
	id("testing-conventions")
	id("spring-conventions")
}

dependencies {
	implementation(project(":be-core"))
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.oauth2.resource.server)
	implementation(libs.springdoc.openapi.starter.webmvc.api)
}
