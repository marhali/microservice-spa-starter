plugins {
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)
	alias(libs.plugins.spring.boot)
	alias(libs.plugins.spring.dependency.management)
	alias(libs.plugins.detekt)
}

allprojects {
	apply(plugin = rootProject.libs.plugins.kotlin.jvm.get().pluginId)

	group = "de.marhali.starter"
	version = "0.0.1-SNAPSHOT"

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(rootProject.libs.versions.java.get())
		}
	}

	repositories {
		mavenCentral()
		maven { url = uri("https://repo.spring.io/milestone") }
		maven { url = uri("https://repo.spring.io/snapshot") }
	}
}

subprojects {
	if (project.name.startsWith("be-")) {
		apply(plugin = rootProject.libs.plugins.kotlin.spring.get().pluginId)
		apply(plugin = rootProject.libs.plugins.spring.boot.get().pluginId)
		apply(plugin = rootProject.libs.plugins.spring.dependency.management.get().pluginId)
		apply(plugin = rootProject.libs.plugins.detekt.get().pluginId)

		dependencies {
			implementation(rootProject.libs.spring.boot.starter.actuator)
			implementation(rootProject.libs.apm.agent.attach)
			testImplementation(rootProject.libs.spring.boot.starter.test)
			testImplementation(rootProject.libs.kotlin.test.junit5)
			testRuntimeOnly(rootProject.libs.junit.platform.launcher)
		}

		dependencyManagement {
			imports {
				mavenBom(rootProject.libs.spring.cloud.dependencies.get().toString())
			}
		}

		kotlin {
			compilerOptions {
				freeCompilerArgs.addAll("-Xjsr305=strict")
			}
		}

		tasks.withType<Test> {
			useJUnitPlatform()
		}
	}
}
