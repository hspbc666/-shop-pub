import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_cart_resp.g.dart';

@JsonSerializable()
class QueryCartResp {
  late int code;
  late dynamic msg;
  late List<CartItem> data;

  QueryCartResp();

  factory QueryCartResp.fromJson(Map<String, dynamic> json) => $QueryCartRespFromJson(json);

  Map<String, dynamic> toJson() => $QueryCartRespToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class CartItem {
  late String id;
  late String goodsId;
  late String name;
  late int price;
  late String longPic;
  late String squarePic;
  late int quantity;

  CartItem();

  factory CartItem.fromJson(Map<String, dynamic> json) => $CartItemFromJson(json);

  Map<String, dynamic> toJson() => $CartItemToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
