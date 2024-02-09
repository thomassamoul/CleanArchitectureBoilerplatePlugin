package com.thomas.cleanBloc.content.feature.domain.repositories

import com.thomas.cleanBloc.utils.convertToCamelCase
import com.thomas.cleanBloc.utils.convertToSnakeCase

fun generateRepositoryBaseContent(featureName: String): String {
    return """
        import 'package:dartz/dartz.dart';

        import '../../../../core/error/exceptions.dart';
        import '../../data/data_sources/${convertToSnakeCase(featureName)}_remote_data_source.dart';
        import '../entities/${convertToSnakeCase(featureName)}.dart';

        abstract class ${convertToCamelCase(featureName)}RepositoryBase {
          final ${convertToCamelCase(featureName)}RemoteDataSourceBase remote;

          ${convertToCamelCase(featureName)}RepositoryBase(this.remote);
           
          Future<Either<GenericException, List<${convertToCamelCase(featureName)}>>> getAll();

          Future<Either<GenericException, ${convertToCamelCase(featureName)}>> getById(int id);

          Future<Either<GenericException, ${convertToCamelCase(featureName)}>> create(${convertToCamelCase(featureName)} entity_name);

          Future<Either<GenericException, ${convertToCamelCase(featureName)}>> update(${convertToCamelCase(featureName)} entity_name);

          Future<Either<GenericException, void>> delete(int id);

          Future<Either<GenericException, void>> deleteAll();
        }

    """.trimIndent()
}