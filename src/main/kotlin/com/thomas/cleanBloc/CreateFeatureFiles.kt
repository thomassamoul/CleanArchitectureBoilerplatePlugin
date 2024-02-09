package com.thomas.cleanBloc

import com.intellij.openapi.vfs.VirtualFile
import com.thomas.cleanBloc.content.feature.data.data_sources.generateEntityRemoteDataSource
import com.thomas.cleanBloc.content.feature.data.models.generateEntityModelContent
import com.thomas.cleanBloc.content.feature.data.repository.generateDataRepository
import com.thomas.cleanBloc.content.feature.domain.entities.generateEntityContent
import com.thomas.cleanBloc.content.feature.domain.repositories.generateRepositoryBaseContent
import com.thomas.cleanBloc.content.feature.domain.use_cases.*
import com.thomas.cleanBloc.content.feature.presentation.manager.generateFeatureCubitContent
import com.thomas.cleanBloc.content.feature.presentation.manager.generateFeatureStateContent
import com.thomas.cleanBloc.content.feature.presentation.pages.generateFeatureScreenContent
import com.thomas.cleanBloc.utils.convertToSnakeCase
import com.thomas.cleanBloc.utils.createFileInDirectory
import com.thomas.cleanBloc.utils.findOrCreateSubdirectory

fun createFeatureFiles(feature: VirtualFile, featureName: String) {
    feature.findOrCreateSubdirectory("data").let { data ->
        createFileInDirectory(
            data.findOrCreateSubdirectory("data_sources"),
            convertToSnakeCase(featureName) + "_remote_data_source.dart",
            generateEntityRemoteDataSource(featureName)
        )
        createFileInDirectory(
            data.findOrCreateSubdirectory("models"),
            convertToSnakeCase(featureName) + "_model.dart",
            generateEntityModelContent(featureName)
        )
        createFileInDirectory(
            data.findOrCreateSubdirectory("repositories"),
            convertToSnakeCase(featureName) + "_repository_impl.dart",
            generateDataRepository(featureName)
        )
    }
    feature.findOrCreateSubdirectory("domain").let { domain ->
        createFileInDirectory(
            domain.findOrCreateSubdirectory("entities"),
            convertToSnakeCase(featureName) + ".dart",
            generateEntityContent(featureName)
        )
        createFileInDirectory(
            domain.findOrCreateSubdirectory("repositories"),
            convertToSnakeCase(featureName) + "_repository_base.dart",
            generateRepositoryBaseContent(featureName)
        )
        domain.findOrCreateSubdirectory("use_cases").let {
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
    feature.findOrCreateSubdirectory("presentation").let { presentation ->
        presentation.findOrCreateSubdirectory("manager").let {
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
        createFileInDirectory(
            presentation.findOrCreateSubdirectory("pages"),
            convertToSnakeCase(featureName) + "_screen.dart",
            generateFeatureScreenContent(featureName)
        )
        presentation.findOrCreateSubdirectory("widgets")
    }
}
