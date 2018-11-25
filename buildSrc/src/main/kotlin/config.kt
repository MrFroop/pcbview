import java.math.BigDecimal

object Config {

    val jvmTargetVersion = "1.8"

    object jacoco {
        val version = "0.8.3-SNAPSHOT"
        val minimumTestCoverage = BigDecimal.valueOf(0.8)
        val xml = true
        val html = true
    }

    val sonarqube = listOf(
        Pair("sonar.organization", "mrfroop-github"),
        Pair("sonar.host.url", "https://sonarcloud.io"),
        Pair("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml"),
        Pair("sonar.java.binaries", "build/classes/kotlin/main")
    )
}
