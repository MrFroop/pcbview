@file:Suppress("UnstableApiUsage")

import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    jacoco
    kotlin("jvm") version "1.3.10"
    id("org.sonarqube") version "2.6.2"
    id("io.gitlab.arturbosch.detekt") version "1.0.0-RC11"
}

repositories {
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    mavenCentral()
    jcenter()
}

application {
    mainClassName = "com.jambren.pcbview.app.PcbView"
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
        properties += Config.sonarqube
    }
}

jacoco {
    toolVersion = Config.jacoco.version
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile(kotlin("reflect"))

    Dependencies.compile.forEach {
        compile(it.toString())
    }

    Dependencies.test.forEach {
        compile(it.toString())
    }

    Dependencies.testRuntime.forEach {
        compile(it.toString())
    }
}

tasks {
    getByName<Test>("test") {
        useJUnitPlatform()
    }

    getByName<KotlinCompile>("compileKotlin") {
        kotlinOptions {
            jvmTarget = Config.jvmTargetVersion
        }
    }

    getByName<KotlinCompile>("compileTestKotlin") {
        kotlinOptions {
            jvmTarget = Config.jvmTargetVersion
        }
    }

    getByName<JacocoReport>("jacocoTestReport") {
        reports {
            xml.apply {
                isEnabled = Config.jacoco.xml
            }

            html.apply {
                isEnabled = Config.jacoco.html
            }
            executionData(tasks.withType<Test>())
        }
    }

    getByName<JacocoCoverageVerification>("jacocoTestCoverageVerification") {
        violationRules {
            rule {
                limit {
                    minimum = Config.jacoco.minimumTestCoverage
                }
            }
        }
    }

    register("beforePush") {
        dependsOn("build", "jacocoTestReport", "jacocoTestCoverageVerification")
    }
}
