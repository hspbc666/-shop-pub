import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_category_resp_entity.g.dart';

@JsonSerializable()
class QueryCategroyResp {
  late int code;
  late dynamic msg;
  late List<QueryCategoryRespData> data;

  QueryCategroyResp();

  factory QueryCategroyResp.fromJson(Map<String, dynamic> json) => $QueryCategroyRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryCategroyRespEntityToJson(this);

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

  factory QueryCategoryRespData.fromJson(Map<String, dynamic> json) => $QueryCategroyRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryCategroyRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
