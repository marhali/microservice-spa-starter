plugins {
	id("java-conventions")
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")


tasks.withType<Test> {
	useJUnitPlatform()
}

dependencies {
	testImplementation(libs.findLibrary("kotlin-test-junit5").get())
	testRuntimeOnly(libs.findLibrary("junit-platform-launcher").get())
}
