package com.thomas.cleanBloc.content.core

fun generateNetworkInfoContent(): String {
    return """
            import 'package:internet_connection_checker/internet_connection_checker.dart';

            abstract class NetworkInfo {
              Future<bool> get isConnected;
            }
            
            class NetworkInfoImpl implements NetworkInfo {
              final InternetConnectionChecker connectionChecker;
            
              NetworkInfoImpl({required this.connectionChecker});
            
              @override
              Future<bool> get isConnected async => await connectionChecker.hasConnection;
            }
        """.trimIndent()
}