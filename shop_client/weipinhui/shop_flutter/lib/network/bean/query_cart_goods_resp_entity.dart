import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_cart_goods_resp_entity.g.dart';

@JsonSerializable()
class QueryCartGoodsRespEntity {
  late int code;
  late dynamic msg;
  late List<QueryCartGoodsRespData> data;

  QueryCartGoodsRespEntity();

  factory QueryCartGoodsRespEntity.fromJson(Map<String, dynamic> json) => $QueryCartGoodsRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryCartGoodsRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryCartGoodsRespData {
  late String id;
  late String goodsId;
  late String name;
  late int price;
  late String squarePic;
  late int quantity;

  QueryCartGoodsRespData();

  factory QueryCartGoodsRespData.fromJson(Map<String, dynamic> json) => $QueryCartGoodsRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryCartGoodsRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
