package com.thomas.cleanarchitectureboilerplate.content.feature.presentation.pages

import com.thomas.cleanarchitectureboilerplate.convertToCamelCase
import com.thomas.cleanarchitectureboilerplate.convertToSnakeCase

fun generateFeatureScreenContent(featureName: String): String {
    return """
        import 'package:flutter/material.dart';

        class ${convertToCamelCase(featureName)}Screen extends StatelessWidget {
          const ${convertToCamelCase(featureName)}Screen({super.key});

          @override
          Widget build(BuildContext context) {
            return const Scaffold();
          }
        }


    """.trimIndent()
}