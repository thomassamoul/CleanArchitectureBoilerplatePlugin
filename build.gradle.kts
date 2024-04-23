plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.22"
    id("org.jetbrains.intellij") version "1.17.2"
    id("org.jetbrains.changelog") version "2.2.0"
}

group = "com.thomas"
version = "1.0.5"

repositories {
    mavenCentral()
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    version.set("2023.1.5")
    type.set("IC") // Target IDE Platform
    updateSinceUntilBuild.set(true)
    plugins.set(listOf("yaml", "java", "Kotlin"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        version.set("1.0.5")
        sinceBuild.set("231.*")
        changeNotes.set(File("${project.projectDir}/CHANGELOG.md").readText())
    }

    changelog {
        path = "${project.projectDir}/CHANGELOG.md"
        keepUnreleasedSection = true
        unreleasedTerm = "[Unreleased]"
        groups.set(listOf("Added", "Changed", "Deprecated", "Removed", "Fixed", "Security"))
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set("perm:dGhvbWFzc2Ftb3Vs.OTItOTU1OA==.dYe0EvW2iGpkyKSJBeOjGUwoGoutNi")
    }
}
