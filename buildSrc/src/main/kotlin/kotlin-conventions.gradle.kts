import gradle.kotlin.dsl.accessors._4a6bac2b1eb1fdd946649436616362ab.implementation

plugins {
	id("java-conventions")
	kotlin("jvm")
	id("io.gitlab.arturbosch.detekt")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

kotlin {
	jvmToolchain {
		languageVersion = JavaLanguageVersion.of(libs.findVersion("java").get().toString())
	}
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

dependencies {
	implementation(libs.findLibrary("kotlin-reflect").get())
}

detekt {
	ignoreFailures = false
	parallel = true
}
