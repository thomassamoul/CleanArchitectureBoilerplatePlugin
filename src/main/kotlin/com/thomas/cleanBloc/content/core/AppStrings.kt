package com.thomas.cleanBloc.content.core

fun generateAppStringsContent():String{
    return """
        class AppStrings {
          static const String appName = 'App Name';
          
          static const String contentType = 'Content-Type';
          static const String applicationJson = 'application/json';
          static const String authorization = "Authorization";
          static const String accessToken = "token";

          static const String undefined = 'undefined';
          static const String noRouteFound = 'No Route Found';
          static const String serverFailure = 'Server Failure';
          static const String cacheFailure = 'Cache Failure';
          static const String unexpectedError = 'Unexpected Error';

          static const String locale = 'locale';
          static const String arabicCode = 'ar';
          static const String englishCode = 'en';

          static const String cachedSignedIn = "CACHED_SIGNED_IN";
          static const String cachedOnBoarded = "CACHED_ONBOARDED";
          static const String cachedCurrentUser = "CACHED_CURRENT_USER";
          static const String cachedToken = "CACHED_TOKEN";

          static const String boolTrue = "true";
          static const String boolFalse = "false";

          static const String body = "body";
          static const String status = "status";
          static const String success = "success";
          static const String message = "message";
          static const String required = "required";

          static const String emptyEmailError = "Email can't be empty";
          static const String emptyPasswordError = "Password can't be empty";
          static const String emptyFirstName = "First Name can't be empty";
          static const String emptyLastName = "Last Name can't be empty";
          static const String emptyPhoneNumber = "Phone Number can't be empty";

          static const String cacheException = "Cache Error";
          static const String notFoundException = "Not Found";
          static const String badRequestException = "Bad Request";
          static const String unauthorizedException = "Unauthorized";
          static const String conflictException = "Conflict Occurred";
          static const String fetchDataException = "Fetch Date Error";
          static const String internalServerErrorException = "Internal Server Error";
          static const String noNetworkConnectionException = "No Internet Connection";
          static const String badCertificateException = "Bad Certificate";
        }
    """.trimIndent()
}