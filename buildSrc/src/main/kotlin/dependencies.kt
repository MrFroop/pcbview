data class Dependency(val name: String, val group: String, val version: String) {
    override fun toString(): String {
        return "$group:$name:$version"
    }
}

object Dependencies {
    val junitVersion = "5.3.1"
    val tornadoFXVersion = "1.7.17"
    val kluentVersion = "1.43"
    val logbackVersion = "1.2.3"
    val mockitoKotlinVersion = "2.0.0"

    val compile = listOf(
        Dependency("tornadofx", "no.tornado", tornadoFXVersion),
        Dependency("logback-classic", "ch.qos.logback", logbackVersion)
    )

    val test = listOf(
        Dependency("junit-jupiter-api", "org.junit.jupiter", junitVersion),
        Dependency("junit-jupiter-params", "org.junit.jupiter", junitVersion),
        Dependency("kluent", "org.amshove.kluent", kluentVersion),
        Dependency("mockito-kotlin", "com.nhaarman.mockitokotlin2", mockitoKotlinVersion)
    )

    val testRuntime = listOf(
        Dependency("junit-jupiter-engine", "org.junit.jupiter", junitVersion)
    )
}

