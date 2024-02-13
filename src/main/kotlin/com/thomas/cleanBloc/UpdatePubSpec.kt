package com.thomas.cleanBloc

import java.io.File

fun updatePubSpec(projectPath: String) {
    val pubspecFile = File("$projectPath/pubspec.yaml")
    if (!pubspecFile.exists()) {
        println("pubspec.yaml file not found.")
        return
    }

    // Read the contents of pubspec.yaml
    val pubspecLines = pubspecFile.readLines().toMutableList()

    // Find the index of the dependencies section
    val dependenciesIndex = pubspecLines.indexOfFirst { it.trim() == "dependencies:" }
    if (dependenciesIndex == -1) {
        println("Dependencies section not found. Adding dependencies section.")
        pubspecLines.add("\ndependencies:")
    }

    // Add dependencies to dependencies section
    val packagesToAdd = mapOf(
        "internet_connection_checker" to "any",
        "dio" to "any",
        "bloc" to "any",
        "flutter_bloc" to "any",
        "shared_preferences" to "any"
    )
    packagesToAdd.forEach { (packageName, version) ->
        val packageIndex = pubspecLines.indexOfFirst { it.contains("$packageName:") }
        if (packageIndex == -1) {
            println("Adding $packageName package.")
            val insertIndex = dependenciesIndex + 1
            pubspecLines.add(insertIndex, "  $packageName: $version")
        }
    }

    // Write the updated pubspec.yaml content back to the file
    pubspecFile.writeText(pubspecLines.joinToString("\n"))

    println("Pubspec.yaml updated successfully.")

    executeTerminalCommand("flutter pub get", projectPath)

}

fun executeTerminalCommand(command: String, projectPath: String) {
    try {
        val commandLine = "cd $projectPath && $command"
        val process = Runtime.getRuntime().exec(arrayOf("bash", "-c", commandLine))
        process.waitFor()
        val output = process.inputStream.bufferedReader().readText()
        val error = process.errorStream.bufferedReader().readText()
        if (output.isNotEmpty()) {
            println("Output: $output")
        }
        if (error.isNotEmpty()) {
            println("Error: $error")
        }
    } catch (e: Exception) {
        println(e.message)
    }
}
