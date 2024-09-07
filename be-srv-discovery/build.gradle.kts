dependencies {
	implementation(project(":be-shared"))
	implementation(libs.spring.cloud.starter.config)
	implementation(libs.spring.cloud.starter.netflix.eureka.server)
}
