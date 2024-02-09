package com.thomas.cleanBloc.content.core

fun createEndPointsContent():String{
    return """
        import 'package:flutter/foundation.dart';

        class EndPoints {
          // base
          static const String baseStagingUrl = 'http://167.86.119.94:4501/api';
          static const String baseProductionUrl = 'http://167.86.119.94:4501/api';
          static const String baseUrl = kDebugMode? baseStagingUrl:baseProductionUrl;
        }
    """.trimIndent()
}