package com.thomas.cleanBloc.content.core

fun createAppInterceptorsContent(): String {
    return """
            import 'package:dio/dio.dart';
            import 'package:flutter/foundation.dart';
            
            import '../utils/app_strings.dart';
            import 'app_authentication.dart';
            
            class AppInterceptors extends Interceptor {
              final Dio client;
            
              final AppAuthentication appAuthentication;
            
              AppInterceptors(this.appAuthentication, this.client);
            
              @override
              void onRequest(RequestOptions options, RequestInterceptorHandler handler) {
                debugPrint('REQUEST[\${'$'}{options.method}] => PATH: \${'$'}{options.path}');
                options.headers[AppStrings.contentType] = AppStrings.applicationJson;
                if (appAuthentication.isUserLogin()) {
                  options.headers[AppStrings.authorization] =
                      'Bearer \${'$'}{appAuthentication.getBearerToken()}';
                }
            
                super.onRequest(options, handler);
              }
            
              @override
              void onResponse(Response response, ResponseInterceptorHandler handler) {
                debugPrint(
                    'RESPONSE[\${'$'}{response.statusCode}] => PATH: \${'$'}{response.requestOptions.path}');
                super.onResponse(response, handler);
              }
            
              @override
              void onError(DioError err, ErrorInterceptorHandler handler) async {
                debugPrint(
                    'ERROR[\${'$'}{err.response?.statusCode}] => PATH: \${'$'}{err.requestOptions.path}');
                if (err.response?.statusCode == 401) {
                  // TODO Handle unauthorized
                }
                super.onError(err, handler);
              }
            }
        """.trimIndent()
}