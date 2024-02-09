package com.thomas.cleanBloc.content.core

fun createEndPointsContent():String{
    return """
        import 'package:flutter/foundation.dart';

        class EndPoints {
          // base
          static const String baseStagingUrl = 'http://localhost/api';
          static const String baseProductionUrl = 'http://localhost/api';
          static const String baseUrl = kDebugMode? baseStagingUrl:baseProductionUrl;
        }
    """.trimIndent()
}