package com.thomas.cleanBloc

import com.intellij.openapi.vfs.VirtualFile
import com.thomas.cleanBloc.content.core.*
import com.thomas.cleanBloc.utils.createFileInDirectory
import com.thomas.cleanBloc.utils.findOrCreateSubdirectory

fun createCoreFiles(core: VirtualFile) {
    core.findOrCreateSubdirectory("api").let {
        createFileInDirectory(it, "api_consumer.dart", generateApiConsumerContent())
        createFileInDirectory(it, "app_interceptors.dart", createAppInterceptorsContent())
        createFileInDirectory(it, "app_authentication.dart", createAppAuthenticationContent())
        createFileInDirectory(it, "dio_consumer.dart", generateDioConsumerContent())
        createFileInDirectory(it, "end_points.dart", createEndPointsContent())
        createFileInDirectory(it, "status_code.dart", createStatusCodeContent())
    }
    createFileInDirectory(core.findOrCreateSubdirectory("di"), "di.dart", generateDiContent())
    createFileInDirectory(core.findOrCreateSubdirectory("error"), "exceptions.dart", generateExceptionsContent())
    createFileInDirectory(core.findOrCreateSubdirectory("models"), "response_model.dart", generateResponseModelContent())
    createFileInDirectory(core.findOrCreateSubdirectory("network"), "network_info.dart", generateNetworkInfoContent())
    createFileInDirectory(core.findOrCreateSubdirectory("useCases"), "use_cases.dart", generateUseCasesContent())
    createFileInDirectory(core.findOrCreateSubdirectory("utils"), "app_strings.dart", generateAppStringsContent())
    createFileInDirectory(core.findOrCreateSubdirectory("utils"), "bloc_observer.dart", generateBlocObserverContent())
    createFileInDirectory(core.findOrCreateSubdirectory("utils"), "hex_color.dart", generateHexColorContent())
}
