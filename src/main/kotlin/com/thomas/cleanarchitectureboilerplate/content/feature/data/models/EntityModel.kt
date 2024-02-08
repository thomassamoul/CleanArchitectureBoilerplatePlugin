package com.thomas.cleanarchitectureboilerplate.content.feature.data.models

import com.thomas.cleanarchitectureboilerplate.convertToCamelCase
import com.thomas.cleanarchitectureboilerplate.convertToSnakeCase

fun generateEntityModelContent(featureName:String):String{
    return """
        import '../../domain/entities/${convertToSnakeCase(featureName)}.dart';

        class ${convertToCamelCase(featureName)}Model extends ${convertToCamelCase(featureName)} {
            ${convertToCamelCase(featureName)}Model({
               required id,
               required name,
            }) : super(id: id, name: name);
            
            factory ${convertToCamelCase(featureName)}Model.fromJson(Map<String, dynamic> json) {
               return ${convertToCamelCase(featureName)}Model(
                   id: json['id'],
                   name: json['name'],
               );
            }
            
            Map<String, dynamic> toJson() {
               return { 
                   'id': id,
                   'name': name,
               };
            }
        }


    """.trimIndent()
}