dependencies {
	implementation(project(":be-core"))
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.spring.cloud.starter.netflix.eureka.server)
}
