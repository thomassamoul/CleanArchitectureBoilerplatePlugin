package com.thomas.cleanBloc.content.core

fun generateAppRouterContent(featureName: String): String {
    return """
            import 'package:go_router/go_router.dart';
            
            abstract class AppRouter {
                static const k${featureName} = '/featureName';
               
                static final router = GoRouter(
                  routes: [
                    GoRoute(
                      path: '/k${featureName}',
                      builder: (context, state) => const ${featureName}View(),
                    ),             
                );
            }
        """.trimIndent()
}