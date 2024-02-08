package com.thomas.cleanarchitectureboilerplate.content.feature.domain.use_cases

import com.thomas.cleanarchitectureboilerplate.convertToCamelCase
import com.thomas.cleanarchitectureboilerplate.convertToSnakeCase

fun generateGetAllEntityUseCase(featureName:String):String{
    return """
        import 'package:dartz/dartz.dart';

        import '../../../../core/error/exceptions.dart';
        import '../../../../core/useCases/use_cases.dart';
        import '../entities/${convertToSnakeCase(featureName)}.dart';
        import '../repositories/${convertToSnakeCase(featureName)}_repository_base.dart';

        class GetAll${convertToCamelCase(featureName)}UseCase extends UseCase<List<${convertToCamelCase(featureName)}>, NoParams> {
           final ${convertToCamelCase(featureName)}RepositoryBase repository;
           
           GetAll${convertToCamelCase(featureName)}UseCase(this.repository);
           
           @override
           Future<Either<GenericException, List<${convertToCamelCase(featureName)}>>> call(NoParams params) async {
               return await repository.getAll();
           }
        }
    """.trimIndent()
}