package com.thomas.cleanBloc.utils

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.vladsch.flexmark.html.HtmlRenderer
import com.vladsch.flexmark.parser.Parser
import java.io.IOException

fun VirtualFile.findOrCreateSubdirectory(name: String): VirtualFile {
    val existingDir = this.findChild(name)
    return existingDir ?: this.createChildDirectory(null, name)
}

fun convertToCamelCase(input: String): String {
    return input.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}

fun convertToSnakeCase(input: String): String {
    val result = StringBuilder()
    for (i in input.indices) {
        val c = input[i]
        if (c.isUpperCase() && i > 0) {
            result.append('_')
            result.append(c.lowercaseChar())
        } else {
            result.append(c)
        }
    }
    return result.toString()
}


fun createFileInDirectory(directory: VirtualFile, fileName: String, content: String, override: Boolean) {
    val file = directory.findOrCreateChildData(null, fileName)
    try {
        if (override || file.contentsToByteArray().toString(Charsets.UTF_8) != content) {
            file.setBinaryContent(content.toByteArray())
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}


fun showErrorDialog(project: Project, message: String) {
    Messages.showErrorDialog(project, message, "Error")
}


fun String.convertMarkdownToHtml(): String {
    val parser = Parser.builder().build()
    val renderer = HtmlRenderer.builder().build()
    val document = parser.parse(this)
    return renderer.render(document)
}