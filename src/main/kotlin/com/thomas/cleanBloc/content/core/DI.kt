package com.thomas.cleanBloc.content.core

fun generateDiContent(): String {
    return """
           import 'package:get_it/get_it.dart';
            import 'package:shared_preferences/shared_preferences.dart';
            
            final getIt = GetIt.instance;
            
            Future<void> init() async {
              final sharedPreferences = await SharedPreferences.getInstance();
              getIt.registerLazySingleton(() => sharedPreferences);
            }
        """.trimIndent()
}