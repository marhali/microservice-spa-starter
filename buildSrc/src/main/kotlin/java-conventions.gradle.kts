val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

plugins {
	`java-library`
	id("base-conventions")
}

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(libs.findVersion("java").get().toString())
	}
}
