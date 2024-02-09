package com.thomas.cleanBloc

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.thomas.cleanBloc.utils.findOrCreateSubdirectory
import com.thomas.cleanBloc.utils.showErrorDialog

fun createFolders(project: Project, featureName: String) {
    WriteCommandAction.runWriteCommandAction(project) {
        val basePath = project.basePath
        basePath?.let { base ->
            val libDirPath = "$base/lib"
            val libDir = LocalFileSystem.getInstance().findFileByPath(libDirPath)
            libDir?.let { lib ->
                createConfigFiles(lib.findOrCreateSubdirectory("config"))

                createCoreFiles(lib.findOrCreateSubdirectory("core"))

                createFeatureFiles(
                    lib.findOrCreateSubdirectory("features").findOrCreateSubdirectory(featureName),
                    featureName
                )
            } ?: showErrorDialog(project, "Failed to find or create 'lib' directory in the project.")
        } ?: showErrorDialog(project, "Failed to get base path of the project.")
    }
}