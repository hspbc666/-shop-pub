import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_cart_resp_entity.g.dart';

@JsonSerializable()
class QueryCartRespEntity {
  late int code;
  late dynamic msg;
  late List<QueryCartRespData> data;

  QueryCartRespEntity();

  factory QueryCartRespEntity.fromJson(Map<String, dynamic> json) => $QueryCartRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryCartRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryCartRespData {
  late String id;
  late String goodsId;
  late String name;
  late int price;
  late String longPic;
  late String squarePic;
  late int quantity;

  QueryCartRespData();

  factory QueryCartRespData.fromJson(Map<String, dynamic> json) => $QueryCartRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryCartRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
