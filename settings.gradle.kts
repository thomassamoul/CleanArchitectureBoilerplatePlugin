pluginManagement {
    repositories {
        mavenCentral()
        maven("https://oss.sonatype.org/content/repositories/snapshots/")
        gradlePluginPortal()
    }
}

buildCache {
    local {
        isEnabled = System.getenv("CI") == null
        directory = File(rootDir, "build/build-cache")
        removeUnusedEntriesAfterDays = 30
    }
}

rootProject.name = "CleanBloc"