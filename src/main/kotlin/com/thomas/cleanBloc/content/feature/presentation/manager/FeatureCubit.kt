package com.thomas.cleanBloc.content.feature.presentation.manager

import com.thomas.cleanBloc.convertToCamelCase
import com.thomas.cleanBloc.convertToSnakeCase

fun generateFeatureCubitContent(featureName: String): String {
    return """
        import 'package:flutter/material.dart';
        import 'package:flutter_bloc/flutter_bloc.dart';        
        import '../../../../core/useCases/use_cases.dart';
        import '../../domain/entities/${convertToSnakeCase(featureName)}.dart';
        import '../../domain/use_cases/create_${convertToSnakeCase(featureName)}_use_case.dart';
        import '../../domain/use_cases/delete_${convertToSnakeCase(featureName)}_use_case.dart';
        import '../../domain/use_cases/get_all_${convertToSnakeCase(featureName)}_use_case.dart';
        import '../../domain/use_cases/get_by_id_${convertToSnakeCase(featureName)}_use_case.dart';
        import '../../domain/use_cases/update_${convertToSnakeCase(featureName)}_use_case.dart';
        
        part '${convertToSnakeCase(featureName)}_state.dart';

        class ${convertToCamelCase(featureName)}Cubit extends Cubit<${convertToCamelCase(featureName)}State> {
          ${convertToCamelCase(featureName)}Cubit({
            required this.create${convertToCamelCase(featureName)}UseCase,
            required this.delete${convertToCamelCase(featureName)}UseCase,
            required this.getAll${convertToCamelCase(featureName)}UseCase,
            required this.getById${convertToCamelCase(featureName)}UseCase,
            required this.update${convertToCamelCase(featureName)}UseCase,
          }) : super(${convertToCamelCase(featureName)}Initial());
        
          final Create${convertToCamelCase(featureName)}UseCase create${convertToCamelCase(featureName)}UseCase;
          final Delete${convertToCamelCase(featureName)}UseCase delete${convertToCamelCase(featureName)}UseCase;
          final GetAll${convertToCamelCase(featureName)}UseCase getAll${convertToCamelCase(featureName)}UseCase;
          final GetById${convertToCamelCase(featureName)}UseCase getById${convertToCamelCase(featureName)}UseCase;
          final Update${convertToCamelCase(featureName)}UseCase update${convertToCamelCase(featureName)}UseCase;
        
          Future<void> create${convertToCamelCase(featureName)}(${convertToCamelCase(featureName)} params) async {
            emit(${convertToCamelCase(featureName)}Loading());
            final result = await create${convertToCamelCase(featureName)}UseCase.call(params);
            result.fold((l) => emit(${convertToCamelCase(featureName)}Failure('Failed To Create ')),
                (r) => emit(${convertToCamelCase(featureName)}Success(result)));
          }
        
          Future<void> delete${convertToCamelCase(featureName)}(int id) async {
            emit(${convertToCamelCase(featureName)}Loading());
            final result = await delete${convertToCamelCase(featureName)}UseCase.call(id);
        
            result.fold((l) => emit(${convertToCamelCase(featureName)}Failure('Failed To Delete ')),
                (r) => emit(${convertToCamelCase(featureName)}Success(result)));
          }
        
          Future<void> getAll${convertToCamelCase(featureName)}s() async {
            emit(${convertToCamelCase(featureName)}Loading());
            final result = await getAll${convertToCamelCase(featureName)}UseCase.call(NoParams());
        
            result.fold((l) => emit(${convertToCamelCase(featureName)}Failure('Failed To Delete ')),
                (r) => emit(${convertToCamelCase(featureName)}Success(result)));
          }
        
          Future<void> get${convertToCamelCase(featureName)}ById(int id) async {
            emit(${convertToCamelCase(featureName)}Loading());
            final result = await getById${convertToCamelCase(featureName)}UseCase.call(id);
        
            result.fold((l) => emit(${convertToCamelCase(featureName)}Failure('Failed To get By id ')),
                (r) => emit(${convertToCamelCase(featureName)}Success(result)));
          }
        
          Future<void> update${convertToCamelCase(featureName)}(${convertToCamelCase(featureName)} ${convertToSnakeCase(featureName)}) async {
            emit(${convertToCamelCase(featureName)}Loading());
            final result = await update${convertToCamelCase(featureName)}UseCase.call(${convertToSnakeCase(featureName)});
        
            result.fold((l) => emit(${convertToCamelCase(featureName)}Failure('Failed To get By id ')),
                (r) => emit(${convertToCamelCase(featureName)}Success(result)));
          }

        }

    """.trimIndent()
}