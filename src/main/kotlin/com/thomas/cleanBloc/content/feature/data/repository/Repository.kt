package com.thomas.cleanBloc.content.feature.data.repository

import com.thomas.cleanBloc.utils.convertToCamelCase
import com.thomas.cleanBloc.utils.convertToSnakeCase


fun generateDataRepository(featureName: String): String {
    return """
        import 'package:dartz/dartz.dart';

        import '../../../../core/error/exceptions.dart';
        import '../../domain/entities/${convertToSnakeCase(featureName)}.dart';
        import '../../domain/repositories/${convertToSnakeCase(featureName)}_repository_base.dart';
        import '../../../../core/network/network_info.dart';

        class ${convertToCamelCase(featureName)}Repository extends ${convertToCamelCase(featureName)}RepositoryBase {
            final NetworkInfo networkInfo;
        
           ${convertToCamelCase(featureName)}Repository(super.remote,{required this.networkInfo});
           
           @override
           Future<Either<GenericException,List<${convertToCamelCase(featureName)}>>> getAll() async {
            if (await networkInfo.isConnected) {               
               try {
                   return Right(await remote.getAll());
               } catch (e) {
                   return Left(UnhandledFailure(message: e.toString()));
               }
            } else {
              return const Left(NoInternetConnectionException());
            }
           }
           
           @override
           Future<Either<GenericException,${convertToCamelCase(featureName)}>> getById(int id) async {
               try {
                   return Right(await remote.getById(id));
               } catch (e) {
                   return Left(UnhandledFailure(message: e.toString()));
               }
            }
            
           @override
           Future<Either<GenericException,${convertToCamelCase(featureName)}>> create(${convertToCamelCase(featureName)} ${featureName}) async {
               try {
                   return Right(await remote.create(${featureName}));
               } catch (e) {
                   return Left(UnhandledFailure(message: e.toString()));
               }
            }
            
           @override
           Future<Either<GenericException,${convertToCamelCase(featureName)}>> update(${convertToCamelCase(featureName)} ${featureName}) async {
               try {
                   return Right(await remote.update(${featureName}));
               } catch (e) {
                    return Left(UnhandledFailure(message: e.toString()));
               }
           }
           
           @override
           Future<Either<GenericException,void>> delete(int id) async {
               try {
                   return Right(await remote.delete(id));
               } catch (e) {
                   return Left(UnhandledFailure(message: e.toString()));
               }
           }
           
           @override
           Future<Either<GenericException,void>> deleteAll() async {
               try {
                   return Right(await remote.deleteAll());
               } catch (e) {
                   return Left(UnhandledFailure(message: e.toString()));
               }
            }
            
            
            
        }

    """.trimIndent()
}