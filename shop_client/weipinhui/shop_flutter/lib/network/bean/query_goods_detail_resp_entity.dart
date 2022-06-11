import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_goods_detail_resp_entity.g.dart';

@JsonSerializable()
class QueryGoodsDetailRespEntity {
  late int code;
  late dynamic msg;
  late QueryGoodsDetailRespData data;

  QueryGoodsDetailRespEntity();

  factory QueryGoodsDetailRespEntity.fromJson(Map<String, dynamic> json) => $QueryGoodsDetailRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryGoodsDetailRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryGoodsDetailRespData {
  late String id;
  late String name;
  late int price;
  late String squarePic;

  QueryGoodsDetailRespData();

  factory QueryGoodsDetailRespData.fromJson(Map<String, dynamic> json) => $QueryGoodsDetailRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryGoodsDetailRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
