package com.thomas.cleanBloc

import com.intellij.openapi.vfs.VirtualFile
import com.thomas.cleanBloc.content.core.*
import com.thomas.cleanBloc.utils.createFileInDirectory
import com.thomas.cleanBloc.utils.findOrCreateSubdirectory

fun createCoreFiles(core: VirtualFile) {
    core.findOrCreateSubdirectory("api").let {
        createFileInDirectory(it, "api_consumer.dart", generateApiConsumerContent(), false)
        createFileInDirectory(it, "app_interceptors.dart", createAppInterceptorsContent(), false)
        createFileInDirectory(it, "app_authentication.dart", createAppAuthenticationContent(), false)
        createFileInDirectory(it, "dio_consumer.dart", generateDioConsumerContent(), false)
        createFileInDirectory(it, "end_points.dart", createEndPointsContent(), false)
        createFileInDirectory(it, "status_code.dart", createStatusCodeContent(), false)
    }
    createFileInDirectory(core.findOrCreateSubdirectory("di"), "di.dart", generateDiContent(), false)
    createFileInDirectory(core.findOrCreateSubdirectory("error"), "exceptions.dart", generateExceptionsContent(), false)
    createFileInDirectory(
        core.findOrCreateSubdirectory("models"),
        "response_model.dart",
        generateResponseModelContent(),
        false
    )
    createFileInDirectory(
        core.findOrCreateSubdirectory("network"),
        "network_info.dart",
        generateNetworkInfoContent(),
        false
    )
    createFileInDirectory(core.findOrCreateSubdirectory("useCases"), "use_cases.dart", generateUseCasesContent(), false)
    createFileInDirectory(
        core.findOrCreateSubdirectory("utils"),
        "app_strings.dart",
        generateAppStringsContent(),
        false
    )
    createFileInDirectory(
        core.findOrCreateSubdirectory("utils"),
        "bloc_observer.dart",
        generateBlocObserverContent(),
        false
    )
    createFileInDirectory(core.findOrCreateSubdirectory("utils"), "hex_color.dart", generateHexColorContent(), false)
}
