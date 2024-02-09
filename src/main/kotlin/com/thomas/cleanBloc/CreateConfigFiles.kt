package com.thomas.cleanBloc

import com.intellij.openapi.vfs.VirtualFile
import com.thomas.cleanBloc.utils.createFileInDirectory
import com.thomas.cleanBloc.utils.findOrCreateSubdirectory

fun createConfigFiles(config: VirtualFile) {
    config.findOrCreateSubdirectory("locale").let {
        createFileInDirectory(it, "app_localizations.dart", "",false)
        createFileInDirectory(it, "app_localizations_delegate.dart", "",false)
        createFileInDirectory(it, "en.json", "",false)
    }
    createFileInDirectory(config.findOrCreateSubdirectory("themes"), "app_theme.dart", "",false)
    createFileInDirectory(config.findOrCreateSubdirectory("routes"), "app_router.dart", "",false)
}
