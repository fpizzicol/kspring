import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

plugins {
	id("org.springframework.boot") version "2.7.2"
	id("io.spring.dependency-management") version "1.0.12.RELEASE"
	kotlin("jvm") version "1.7.20"
	kotlin("plugin.spring") version "1.7.20"
}

group = "tech.talk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}

	dependsOn("registering")
}

tasks.register<WriteProperties>("registering") {
	outputFile = file("src/main/resources/application.properties")
	properties(
		mapOf(
			"server.port" to 4567,
			"build.group" to project.group,
			"build.name" to project.name,
			"build.version" to project.version,
			"build.timestamp" to buildTime()
		)
	)
}

fun buildTime(): String? {
	val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
	df.timeZone = TimeZone.getTimeZone("UTC")
	return df.format(Date())
}

tasks.withType<Test> {
	useJUnitPlatform()
}