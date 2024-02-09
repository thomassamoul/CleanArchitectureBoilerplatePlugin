package com.thomas.cleanBloc.content.feature.domain.use_cases

import com.thomas.cleanBloc.convertToCamelCase
import com.thomas.cleanBloc.convertToSnakeCase

fun generateGetAllByIdEntityUseCase(featureName:String):String{
    return """
        import 'package:dartz/dartz.dart';

        import '../../../../core/error/exceptions.dart';
        import '../../../../core/useCases/use_cases.dart';
        import '../entities/${convertToSnakeCase(featureName)}.dart';
        import '../repositories/${convertToSnakeCase(featureName)}_repository_base.dart';

        class GetById${convertToCamelCase(featureName)}UseCase extends UseCase<${convertToCamelCase(featureName)}, int> {
           final ${convertToCamelCase(featureName)}RepositoryBase repository;
           
           GetById${convertToCamelCase(featureName)}UseCase(this.repository);
           
           @override
           Future<Either<GenericException, ${convertToCamelCase(featureName)}>> call(int id) async {
               return await repository.getById(id);
           }
        }
    """.trimIndent()
}