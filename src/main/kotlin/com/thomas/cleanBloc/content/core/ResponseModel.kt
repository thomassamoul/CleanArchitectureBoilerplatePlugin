package com.thomas.cleanBloc.content.core

fun generateResponseModelContent(): String {
    return """
            import 'package:equatable/equatable.dart';

            class ResponseModel<T> extends Equatable {
              final List<T>? data;
              final num? pageIndex;
              final num? pageSize;
              final num? count;
              final bool? status;
              final String? message;
            
              const ResponseModel(
                  {required this.data,
                  required this.pageIndex,
                  required this.pageSize,
                  required this.count,
                  required this.status,
                  required this.message});
            
              factory ResponseModel.fromJson(Map<String, dynamic> json, Function fromJson) {
                final List<dynamic> jsonData = json['data'];
                final List<T> items = jsonData.map<T>((item) => fromJson(item)).toList();
            
                return ResponseModel<T>(
                  data: items,
                  pageIndex: json['pageIndex'] as num?,
                  pageSize: json['pageSize'] as num?,
                  count: json['count'] as num?,
                  status: json['status'] as bool?,
                  message: json["message"] as String?,
                );
              }
            
              @override
              List<Object?> get props => [
                    data,
                    pageIndex,
                    pageSize,
                    count,
                    status,
                    message,
                  ];
            }

        """.trimIndent()
}