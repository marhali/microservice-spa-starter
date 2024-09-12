plugins {
	id("kotlin-conventions")
	id("testing-conventions")
	id("spring-conventions")
}

dependencies {
	compileOnly(libs.spring.boot.starter.security)
}
