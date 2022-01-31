import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_category_resp_entity.g.dart';

@JsonSerializable()
class QueryCategoryRespEntity {
  late int code;
  late dynamic msg;
  late List<QueryCategoryRespData> data;

  QueryCategoryRespEntity();

  factory QueryCategoryRespEntity.fromJson(Map<String, dynamic> json) => $QueryCategoryRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryCategoryRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryCategoryRespData {
  late String id;
  late String name;

  QueryCategoryRespData();

  factory QueryCategoryRespData.fromJson(Map<String, dynamic> json) => $QueryCategoryRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryCategoryRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
