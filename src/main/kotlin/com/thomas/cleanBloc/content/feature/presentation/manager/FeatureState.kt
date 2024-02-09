package com.thomas.cleanBloc.content.feature.presentation.manager

import com.thomas.cleanBloc.convertToCamelCase
import com.thomas.cleanBloc.convertToSnakeCase

fun generateFeatureStateContent(featureName: String): String {
    return """
        part of '${convertToSnakeCase(featureName)}_cubit.dart';

        @immutable
        abstract class ${convertToCamelCase(featureName)}State<T> {}

        class ${convertToCamelCase(featureName)}Initial extends ${convertToCamelCase(featureName)}State {}

        class ${convertToCamelCase(featureName)}Loading<T> extends ${convertToCamelCase(featureName)}State<T> {}

        class ${convertToCamelCase(featureName)}Success<T> extends ${convertToCamelCase(featureName)}State<T> {
          final T data;

          ${convertToCamelCase(featureName)}Success(this.data);
        }

        class ${convertToCamelCase(featureName)}Failure<T> extends ${convertToCamelCase(featureName)}State<T> {
          final String errorMessage;

          ${convertToCamelCase(featureName)}Failure(this.errorMessage);
        }

    """.trimIndent()
}