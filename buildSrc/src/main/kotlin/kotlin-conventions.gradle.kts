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

detekt {
	ignoreFailures = false
	parallel = true
}
