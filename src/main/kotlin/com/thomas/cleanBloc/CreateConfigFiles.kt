package com.thomas.cleanBloc

import com.intellij.openapi.vfs.VirtualFile
import com.thomas.cleanBloc.utils.createFileInDirectory
import com.thomas.cleanBloc.utils.findOrCreateSubdirectory

fun createConfigFiles(config: VirtualFile) {
    config.findOrCreateSubdirectory("locale").let {
        createFileInDirectory(it, "app_localizations.dart", "")
        createFileInDirectory(it, "app_localizations_delegate.dart", "")
        createFileInDirectory(it, "en.json", "")
    }
    createFileInDirectory(config.findOrCreateSubdirectory("themes"), "app_theme.dart", "")
    createFileInDirectory(config.findOrCreateSubdirectory("routes"), "app_router.dart", "")
}
