package com.thomas.cleanBloc.content.feature.domain.entities

import com.thomas.cleanBloc.convertToCamelCase

fun generateEntityContent(featureName:String):String{
    return """
        abstract class ${convertToCamelCase(featureName)} {
           final int id;
           final String name;
            
           ${convertToCamelCase(featureName)}({
               required this.id,
               required this.name,
           });
        }
    """.trimIndent()
}