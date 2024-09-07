dependencies {
	implementation(project(":be-core"))
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.oauth2.client)
	implementation(libs.spring.boot.starter.oauth2.authorization.server)
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.springdoc.openapi.starter.webmvc.api)
}
