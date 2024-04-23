package com.thomas.cleanBloc

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.vfs.LocalFileSystem
import java.io.File

class MenuAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = EntityServiceInputDialog()

        if (dialog.showAndGet()) {
            val project = e.project
            project?.basePath?.let { updatePubSpec(it) }
            project?.let { createFolders(it, dialog.featureName.text,dialog.addFeatureOnlyCheckbox.isSelected) }

        }
    }
}

