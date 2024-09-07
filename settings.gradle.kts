rootProject.name = "microservice-spa-starter"

pluginManagement {
	repositories {
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
		mavenCentral()
		gradlePluginPortal()
	}
}

include("be-core")
include("be-srv-config")
include("be-srv-discovery")
include("be-srv-auth")
include("be-srv-gateway")
include("be-srv-todo")
include("fe-app-spa")
