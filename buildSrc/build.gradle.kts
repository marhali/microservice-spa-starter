plugins {
	`kotlin-dsl`
}

val libs: VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

repositories {
	gradlePluginPortal()
	maven { url = uri("https://repo.spring.io/milestone") }
	mavenCentral()
}

kotlin {
	jvmToolchain {
		languageVersion = JavaLanguageVersion.of(libs.findVersion("java").get().toString())
	}
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

dependencies {
	implementation(libs.findLibrary("kotlin-gradle").get())
	implementation(libs.findLibrary("kotlin-allopen").get())
	implementation(libs.findLibrary("kotlin-noarg").get())
	implementation(libs.findLibrary("spring-boot-gradle").get())
	implementation(libs.findLibrary("detekt-gradle").get())
}

