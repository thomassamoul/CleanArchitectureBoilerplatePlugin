package com.thomas.cleanarchitectureboilerplate

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.process.ProcessTerminatedListener
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.client.currentSession
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.thomas.cleanarchitectureboilerplate.content.core.*
import com.thomas.cleanarchitectureboilerplate.content.feature.data.data_sources.generateEntityRemoteDataSource
import com.thomas.cleanarchitectureboilerplate.content.feature.data.models.generateEntityModelContent
import com.thomas.cleanarchitectureboilerplate.content.feature.data.repository.generateDataRepository
import com.thomas.cleanarchitectureboilerplate.content.feature.domain.entities.generateEntityContent
import com.thomas.cleanarchitectureboilerplate.content.feature.domain.repositories.generateRepositoryBaseContent
import com.thomas.cleanarchitectureboilerplate.content.feature.domain.use_cases.*
import com.thomas.cleanarchitectureboilerplate.content.feature.presentation.manager.generateFeatureCubitContent
import com.thomas.cleanarchitectureboilerplate.content.feature.presentation.manager.generateFeatureStateContent
import com.thomas.cleanarchitectureboilerplate.content.feature.presentation.pages.generateFeatureScreenContent
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

class MenuAction : AnAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val dialog = EntityServiceInputDialog()
        if (dialog.showAndGet()) {
            val project = e.project
//            executeTerminalCommand("flutter pub add internet_connection_checker", project)
            project?.let { createFolders(it, dialog.featureName.text) }

        }
    }

    private fun createFolders(project: Project, featureName: String) {
        WriteCommandAction.runWriteCommandAction(project) {
            val basePath = project.basePath
            basePath?.let { base ->
                val libDirPath = "$base/lib"
                val libDir = LocalFileSystem.getInstance().findFileByPath(libDirPath)
                libDir?.let { lib ->
                    lib.findOrCreateSubdirectory("config").let { config ->
                        config.findOrCreateSubdirectory("locale").let {
                            createFileInDirectory(it, "app_localizations.dart", "")
                            createFileInDirectory(it, "app_localizations_delegate.dart", "")
                            createFileInDirectory(it, "en.json", "")
                        }
                        createFileInDirectory(config.findOrCreateSubdirectory("themes"), "app_theme.dart", "")
                        createFileInDirectory(config.findOrCreateSubdirectory("routes"), "app_router.dart", "")
                    }

                    lib.findOrCreateSubdirectory("core").let { core ->
                        core.findOrCreateSubdirectory("api").let {
                            createFileInDirectory(it, "api_consumer.dart", generateApiConsumerContent())
                            createFileInDirectory(it, "app_interceptors.dart", createAppInterceptorsContent())
                            createFileInDirectory(it, "app_authentication.dart", createAppAuthenticationContent())
                            createFileInDirectory(it, "dio_consumer.dart", generateDioConsumerContent())
                            createFileInDirectory(it, "end_points.dart", createEndPointsContent())
                            createFileInDirectory(it, "status_code.dart", createStatusCodeContent())
                        }
                        core.findOrCreateSubdirectory("di").let {
                            createFileInDirectory(it, "di.dart", generateDiContent())
                        }
                        core.findOrCreateSubdirectory("error").let {
                            createFileInDirectory(it, "exceptions.dart", generateExceptionsContent())
                        }
                        core.findOrCreateSubdirectory("models").let {
                            createFileInDirectory(it, "response_model.dart", generateResponseModelContent())
                        }
                        core.findOrCreateSubdirectory("network").let {
                            createFileInDirectory(it, "network_info.dart", generateNetworkInfoContent())
                        }
                        core.findOrCreateSubdirectory("useCases").let {
                            createFileInDirectory(it, "use_cases.dart", generateUseCasesContent())
                        }
                        core.findOrCreateSubdirectory("utils").let {
                            createFileInDirectory(it, "app_strings.dart", generateAppStringsContent())
                        }
                        core.findOrCreateSubdirectory("utils").let {
                            createFileInDirectory(it, "bloc_observer.dart", generateBlocObserverContent())
                        }
                        core.findOrCreateSubdirectory("utils").let {
                            createFileInDirectory(it, "hex_color.dart", generateHexColorContent())
                        }
                    }

                    lib.findOrCreateSubdirectory("features").let { featuresDir ->
                        featuresDir.findOrCreateSubdirectory(featureName).let { feature ->

                            feature.findOrCreateSubdirectory("data").let { data ->
                                data.findOrCreateSubdirectory("data_sources").let {
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + "_remote_data_source.dart",
                                        generateEntityRemoteDataSource(featureName)
                                    )
                                }
                                data.findOrCreateSubdirectory("models").let {
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + "_model.dart",
                                        generateEntityModelContent(featureName)
                                    )
                                }
                                data.findOrCreateSubdirectory("repositories").let {
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + "_repository_impl.dart",
                                        generateDataRepository(featureName)
                                    )
                                }
                            }
                            feature.findOrCreateSubdirectory("domain").let {
                                it.findOrCreateSubdirectory("entities").let {
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + ".dart",
                                        generateEntityContent(featureName)
                                    )
                                }
                                it.findOrCreateSubdirectory("repositories").let {
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + "_repository_base.dart",
                                        generateRepositoryBaseContent(featureName)
                                    )
                                }
                                it.findOrCreateSubdirectory("use_cases").let {
                                    createFileInDirectory(
                                        it,
                                        "create_" + convertToSnakeCase(featureName) + "_use_case.dart",
                                        generateCreateEntityUseCase(featureName)
                                    )
                                    createFileInDirectory(
                                        it,
                                        "delete_" + convertToSnakeCase(featureName) + "_use_case.dart",
                                        generateDeleteEntityUseCase(featureName)
                                    )
                                    createFileInDirectory(
                                        it,
                                        "get_all_" + convertToSnakeCase(featureName) + "_use_case.dart",
                                        generateGetAllEntityUseCase(featureName)
                                    )
                                    createFileInDirectory(
                                        it,
                                        "get_by_id_" + convertToSnakeCase(featureName) + "_use_case.dart",
                                        generateGetAllByIdEntityUseCase(featureName)
                                    )
                                    createFileInDirectory(
                                        it,
                                        "update_" + convertToSnakeCase(featureName) + "_use_case.dart",
                                        generateUpdateEntityUseCase(featureName)
                                    )
                                }
                            }
                            feature.findOrCreateSubdirectory("presentation").let {
                                it.findOrCreateSubdirectory("manager").let {
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + "_cubit.dart",
                                        generateFeatureCubitContent(featureName)
                                    )
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + "_state.dart",
                                        generateFeatureStateContent(featureName)
                                    )
                                }
                                it.findOrCreateSubdirectory("pages").let {
                                    createFileInDirectory(
                                        it,
                                        convertToSnakeCase(featureName) + "_screen.dart",
                                        generateFeatureScreenContent(featureName)
                                    )
                                }
                                it.findOrCreateSubdirectory("widgets")
                            }
                        }
                    }

                } ?: run {
                    Messages.showErrorDialog(
                        project,
                        "Failed to find or create 'lib' directory in the project.",
                        "Error"
                    )
                }
            } ?: run {
                Messages.showErrorDialog(project, "Failed to get base path of the project.", "Error")
            }

        }

    }

    private fun showSuccessMessage(project: Project, entityName: String) {
        project.let {
            val message = "Folders created successfully:\nFeature: $entityName\n"
            Messages.showMessageDialog(it, message, "Success", Messages.getInformationIcon())
        }
    }

    private fun createFileInDirectory(directory: VirtualFile, fileName: String, content: String) {

        val file = directory.findOrCreateChildData(null, fileName)
        try {
            file.setBinaryContent(content.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun executeTerminalCommand(command: String, project: Project?) {
        project?.let {
            val commandLine = GeneralCommandLine()
            commandLine.exePath = System.getenv("SHELL") ?: "/bin/bash" // Default to bash if SHELL is not set
            commandLine.addParameters("-c", command)
            val processHandler: ProcessHandler = OSProcessHandler(commandLine)
            ProcessTerminatedListener.attach(processHandler)
            processHandler.startNotify()
        }
    }

}

private fun VirtualFile.findOrCreateSubdirectory(name: String): VirtualFile {
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
