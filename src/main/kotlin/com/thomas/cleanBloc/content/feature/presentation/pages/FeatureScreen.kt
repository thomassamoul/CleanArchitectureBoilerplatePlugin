package com.thomas.cleanBloc.content.feature.presentation.pages

import com.thomas.cleanBloc.utils.convertToCamelCase

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