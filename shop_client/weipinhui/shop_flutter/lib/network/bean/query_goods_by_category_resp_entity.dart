import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_goods_by_category_resp_entity.g.dart';

@JsonSerializable()
class QueryGoodsByCategoryRespEntity {
  late int code;
  late dynamic msg;
  late List<QueryGoodsByCategoryRespData> data;

  QueryGoodsByCategoryRespEntity();

  factory QueryGoodsByCategoryRespEntity.fromJson(Map<String, dynamic> json) =>
      $QueryGoodsByCategoryRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryGoodsByCategoryRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryGoodsByCategoryRespData {
  late String id;
  late String name;
  late int price;
  late String squarePic;

  QueryGoodsByCategoryRespData();

  factory QueryGoodsByCategoryRespData.fromJson(Map<String, dynamic> json) =>
      $QueryGoodsByCategoryRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryGoodsByCategoryRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
