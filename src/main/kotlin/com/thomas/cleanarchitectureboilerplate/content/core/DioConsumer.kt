package com.thomas.cleanarchitectureboilerplate.content.core

fun generateDioConsumerContent():String{
    return """
        import 'dart:convert';
        import 'dart:io';

        import 'package:dio/dio.dart';
        import 'package:dio/io.dart';
        import './status_code.dart';
        import 'package:flutter/foundation.dart';

        import '../di/di.dart';
        import '../error/exceptions.dart';
        import 'api_consumer.dart';
        import 'app_interceptors.dart';
        import 'end_points.dart';

        class DioConsumer implements ApiConsumer {
          final Dio client;

          DioConsumer({required this.client}) {
            client.httpClientAdapter = IOHttpClientAdapter(
              createHttpClient: () {
                final client = HttpClient();
                client.badCertificateCallback =
                    (X509Certificate cert, String host, int port) => true;
                return client;
              },
            );

            client.options
              ..baseUrl = EndPoints.baseStagingUrl
              ..responseType = ResponseType.plain
              ..followRedirects = false;
            client.interceptors.add(getIt<AppInterceptors>());
            if (kDebugMode) {
              client.interceptors.add(getIt<LogInterceptor>());
            }
          }

          @override
          Future<dynamic> get(
            String path, {
            Map<String, dynamic>? queryParameters,
            Map<String, String>? headers,
          }) async {
            try {
              final response = await client.get(
                path,
                queryParameters: queryParameters,
                options: Options(
                  headers: headers,
                ),
              );
              return _handleResponseAsJson(response);
            } on DioException catch (error) {
              _handleDioError(error);
            }
          }

          @override
          Future<dynamic> post(String path,
              {Map<String, dynamic>? body,
              bool formDataIsEnabled = false,
              Map<String, String>? headers,
              Map<String, dynamic>? queryParameters}) async {
            try {
              final response = await client.post(path,
                  data: formDataIsEnabled ? FormData.fromMap(body!) : body,
                  options: Options(
                    headers: headers,
                  ),
                  queryParameters: queryParameters);

              return _handleResponseAsJson(response);
            } on DioException catch (error) {
              _handleDioError(error);
            }
          }

          @override
          Future<dynamic> delete(String path,
              {Map<String, dynamic>? body,
              Map<String, String>? headers,
              Map<String, dynamic>? queryParameters}) async {
            try {
              final response = await client.delete(path,
                  data: body,
                  options: Options(
                    headers: headers,
                  ),
                  queryParameters: queryParameters);
              return _handleResponseAsJson(response);
            } on DioException catch (error) {
              _handleDioError(error);
            }
          }

          @override
          Future<dynamic> put(String path,
              {Map<String, dynamic>? body,
              Map<String, String>? headers,
              bool responseIsParsing = true,
              Map<String, dynamic>? queryParameters}) async {
            try {
              final response = await client.put(
                path,
                data: body,
                queryParameters: queryParameters,
                options: Options(
                  headers: headers,
                ),
              );

              return _handleResponseAsJson(response);
            } on DioException catch (error) {
              _handleDioError(error);
            }
          }

          dynamic _handleResponseAsJson(Response<dynamic> response) {
            final responseJson = jsonDecode(response.data.toString());
            return responseJson;
          }

          dynamic _handleDioError(DioException error) {
            switch (error.type) {
              case DioExceptionType.connectionTimeout:
              case DioExceptionType.sendTimeout:
              case DioExceptionType.receiveTimeout:
                throw const FetchDataException();
              case DioExceptionType.badResponse:
                switch (error.response?.statusCode) {
                  case StatusCode.badRequest:
                    throw const BadRequestException();
                  case StatusCode.unauthorized:
                  case StatusCode.forbidden:
                    throw const UnauthorizedException();
                  case StatusCode.notFound:
                    throw const NotFoundException();
                  case StatusCode.conflict:
                    throw const ConflictException();
                  case StatusCode.internalServerError:
                    throw const InternalServerErrorException();
                }
                break;
              case DioExceptionType.cancel:
                break;
              case DioExceptionType.unknown:
                throw const NoInternetConnectionException();
              case DioExceptionType.badCertificate:
                throw const BadCertificateException();
              case DioExceptionType.connectionError:
                throw const NoInternetConnectionException();
            }
          }
        }
    """.trimIndent()
}