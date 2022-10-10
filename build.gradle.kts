import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

buildscript {
    repositories { mavenCentral() }

    dependencies {
        val kotlinVersion = "1.7.20"
        classpath(kotlin("gradle-plugin", version = kotlinVersion))
        classpath(kotlin("serialization", version = kotlinVersion))
    }
}
repositories {
    mavenCentral()
}


dependencies {
    //Kotlin
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.20")

    //Ktor
    implementation("io.ktor:ktor-server-core:2.1.2")
    implementation("io.ktor:ktor-server-netty:2.1.2")
    implementation("io.ktor:ktor-server-call-logging:2.1.2")
    implementation("io.ktor:ktor-server-content-negotiation:2.1.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.1.2")
    testImplementation("io.ktor:ktor-server-test-host:2.1.2")

    //Koin
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("io.insert-koin:koin-ktor:3.2.2")
    testImplementation("io.insert-koin:koin-test:3.2.2")

    implementation("com.squareup.okhttp3:okhttp:4.10.0")
    implementation("ch.qos.logback:logback-classic:1.4.3")
}

plugins {
    kotlin("jvm") version "1.7.20"
    kotlin("plugin.serialization") version "1.7.20"
    id("io.ktor.plugin") version "2.1.2"
}

group = "tech.talk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

application {
    mainClass.set("tech.talk.kspring.KspringApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
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
            "written.by" to "Filippo Pizzicola",
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