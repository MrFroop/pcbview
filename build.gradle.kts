import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    kotlin("jvm") version "1.3.10"
    id("org.sonarqube") version "2.6.2"
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC11"
}

repositories {
    mavenCentral()
    jcenter()
}

val junitVersion = "5.3.1"
val tornadoFXVersion = "1.7.17"

application {
    mainClassName = "com.jambren.pcbview.app.PcbView"
}

val compileKotlin: KotlinCompile by tasks

compileKotlin.kotlinOptions.jvmTarget = "1.8"

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))

    compile(group = "no.tornado", name = "tornadofx", version = tornadoFXVersion)

    testCompile(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
    testRuntime(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
}

detekt {
    input = files(
        "src/main/kotlin"
    )

    reports {
        xml {
            destination = file("build/reports/detekt.xml")
        }
    }
}