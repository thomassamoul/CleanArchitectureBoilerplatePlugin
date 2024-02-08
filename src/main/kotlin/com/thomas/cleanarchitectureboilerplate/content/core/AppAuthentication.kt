package com.thomas.cleanarchitectureboilerplate.content.core

fun createAppAuthenticationContent():String{
    return """
        import 'dart:convert';

        import 'package:shared_preferences/shared_preferences.dart';

        import '../utils/app_strings.dart';

        abstract class AppAuthentication {
          String getBearerToken();

          bool isUserLogin();

          bool isUserBoarded();
        }

        class AppAuthenticationImpl extends AppAuthentication {
          final SharedPreferences sharedPreferences;

          AppAuthenticationImpl({required this.sharedPreferences});

          @override
          String getBearerToken() {
            var signingClaims =
                jsonDecode(sharedPreferences.getString(AppStrings.cachedCurrentUser)!)
                    as Map<String, dynamic>;
            return signingClaims[AppStrings.accessToken];
          }

          @override
          bool isUserBoarded() {
            return sharedPreferences.getBool(AppStrings.cachedOnBoarded) ?? false;
          }

          @override
          bool isUserLogin() {
            return sharedPreferences.getBool(AppStrings.cachedSignedIn) ?? false;
          }
        }

    """.trimIndent()
}