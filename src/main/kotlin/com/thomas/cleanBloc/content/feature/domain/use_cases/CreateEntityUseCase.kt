package com.thomas.cleanBloc.content.feature.domain.use_cases

import com.thomas.cleanBloc.utils.convertToCamelCase
import com.thomas.cleanBloc.utils.convertToSnakeCase


fun generateCreateEntityUseCase(featureName: String): String {
    return """
        import 'package:dartz/dartz.dart';

        import '../../../../core/error/exceptions.dart';
        import '../../../../core/useCases/use_cases.dart';
        import '../entities/${convertToSnakeCase(featureName)}.dart';
        import '../repositories/${convertToSnakeCase(featureName)}_repository_base.dart';

        class Create${convertToCamelCase(featureName)}UseCase extends UseCase<${convertToCamelCase(featureName)}, ${
        convertToCamelCase(
            featureName
        )
    }> {
           final ${convertToCamelCase(featureName)}RepositoryBase repository;
           
           Create${convertToCamelCase(featureName)}UseCase(this.repository);
           
           @override
           Future<Either<GenericException, ${convertToCamelCase(featureName)}>> call(${convertToCamelCase(featureName)} params) async {
               return await repository.create(params);
           }
        }
    """.trimIndent()
}