package com.thomas.cleanBloc.content.feature.domain.use_cases

import com.thomas.cleanBloc.convertToCamelCase
import com.thomas.cleanBloc.convertToSnakeCase

fun generateDeleteEntityUseCase(featureName:String):String{
    return """
        import 'package:dartz/dartz.dart';

        import '../../../../core/error/exceptions.dart';
        import '../../../../core/useCases/use_cases.dart';
        import '../entities/${convertToSnakeCase(featureName)}.dart';
        import '../repositories/${convertToSnakeCase(featureName)}_repository_base.dart';

        class Delete${convertToCamelCase(featureName)}UseCase extends UseCase<void, int> {
           final ${convertToCamelCase(featureName)}RepositoryBase repository;
           
           Delete${convertToCamelCase(featureName)}UseCase(this.repository);
           
           @override
           Future<Either<GenericException, void>> call(int params) async {
               return await repository.delete(params);
           }
        }
    """.trimIndent()
}