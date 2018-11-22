import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
	kotlin("jvm") version "1.3.10"
    id("org.sonarqube") version "2.6.2"
}

repositories {
    mavenCentral()
}

val junitVersion = "5.3.1"
val tornadofxVersion = "1.7.17"

application {
	mainClassName = "com.jambren.pcbview.app.PcbView"
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"

dependencies {
    compile(kotlin("stdlib"))

    compile(group = "no.tornado", name = "tornadofx", version = tornadofxVersion)

    testCompile(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
    testRuntime(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
}