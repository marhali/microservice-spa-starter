dependencies {
	implementation(project(":be-shared"))
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.spring.boot.starter.web)
	implementation(libs.spring.boot.starter.security)
	implementation(libs.spring.boot.starter.oauth2.resource.server)
	implementation(libs.springdoc.openapi.starter.webmvc.api)
}
