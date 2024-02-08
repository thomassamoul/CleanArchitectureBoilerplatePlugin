package com.thomas.cleanarchitectureboilerplate.content.feature.domain.entities

import com.thomas.cleanarchitectureboilerplate.convertToCamelCase
import com.thomas.cleanarchitectureboilerplate.convertToSnakeCase

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