package com.thomas.cleanarchitectureboilerplate.content.feature.data.data_sources

import com.thomas.cleanarchitectureboilerplate.convertToCamelCase
import com.thomas.cleanarchitectureboilerplate.convertToSnakeCase

fun generateEntityRemoteDataSource(featureName:String):String{
    return """
        import '../../../../core/api/api_consumer.dart';
        import '../../domain/entities/${convertToSnakeCase(featureName)}.dart';

        abstract class ${convertToCamelCase(featureName)}RemoteDataSourceBase {
          final ApiConsumer apiConsumer;

          ${convertToCamelCase(featureName)}RemoteDataSourceBase(this.apiConsumer);

          Future<List<${convertToCamelCase(featureName)}>> getAll();

          Future<${convertToCamelCase(featureName)}> getById(int id);

          Future<${convertToCamelCase(featureName)}> create(${convertToCamelCase(featureName)} ${featureName});

          Future<${convertToCamelCase(featureName)}> update(${convertToCamelCase(featureName)} ${featureName});

          Future<void> delete(int id);

          Future<void> deleteAll();
        }

        class ${convertToCamelCase(featureName)}RemoteDataSource extends ${convertToCamelCase(featureName)}RemoteDataSourceBase {
          ${convertToCamelCase(featureName)}RemoteDataSource(super.http);

          @override
          Future<List<${convertToCamelCase(featureName)}>> getAll() async {
            // TODO implements
            throw UnimplementedError();
          }

          @override
          Future<${convertToCamelCase(featureName)}> getById(int id) async {
            // TODO implements
            throw UnimplementedError();
          }

          @override
          Future<${convertToCamelCase(featureName)}> create(${convertToCamelCase(featureName)} ${featureName}) async {
            // TODO implements
            throw UnimplementedError();
          }

          @override
          Future<${convertToCamelCase(featureName)}> update(${convertToCamelCase(featureName)} ${featureName}) async {
            // TODO implements
            throw UnimplementedError();
          }

          @override
          Future<void> delete(int id) async {
            // TODO implements
            throw UnimplementedError();
          }

          @override
          Future<void> deleteAll() async {
            // TODO implements
            throw UnimplementedError();
          }
        }

    """.trimIndent()
}