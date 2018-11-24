import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    jacoco
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
val kluentVersion = "1.43"
val logbackVersion = "1.2.3"

val minimumTestCoverage = 0.8

application {
    mainClassName = "com.jambren.pcbview.app.PcbView"
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))

    compile(group = "no.tornado", name = "tornadofx", version = tornadoFXVersion)
    compile(group = "ch.qos.logback", name = "logback-classic", version = logbackVersion)

    testCompile(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
    testCompile(group = "org.junit.jupiter", name = "junit-jupiter-params", version = junitVersion)
    testCompile(group = "org.amshove.kluent", name = "kluent", version = kluentVersion)

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

sonarqube {
    properties {
        properties["sonar.organization"] = "mrfroop-github"
        properties["sonar.host.url"] = "https://sonarcloud.io"

        properties["sonar.coverage.jacoco.xmlReportPaths"] = "build/reports/jacoco/test/jacocoTestReport.xml"
        properties["sonar.java.binaries"] = "build/classes/kotlin/main"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

tasks.withType<JacocoReport> {
    reports {
        xml.apply {
            isEnabled = true
        }

        html.apply {
            isEnabled = true
        }
        executionData(tasks.withType<Test>())
    }
}

tasks.withType<JacocoCoverageVerification> {
    violationRules {
        rule {
            limit {
                minimum = BigDecimal.valueOf(minimumTestCoverage)
            }
        }
    }
}
