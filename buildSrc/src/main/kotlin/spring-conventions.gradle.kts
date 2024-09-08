plugins {
	id("java-conventions")
	kotlin("plugin.spring")
	id("org.springframework.boot")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

apply(plugin = "org.jetbrains.kotlin.plugin.spring")
apply(plugin = "org.springframework.boot")
apply(plugin = "io.spring.dependency-management")

dependencies {
	implementation(platform(libs.findLibrary("spring-cloud-dependencies").get()))
	implementation(libs.findLibrary("spring-boot-starter-actuator").get())
	implementation(libs.findLibrary("apm-agent-attach").get())
	developmentOnly(libs.findLibrary("spring-boot-devtools").get())
	testImplementation(libs.findLibrary("spring-boot-starter-test").get())
}

springBoot {
	buildInfo()
}
