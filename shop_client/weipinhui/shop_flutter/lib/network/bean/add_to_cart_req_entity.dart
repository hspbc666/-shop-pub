import 'dart:convert';
import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/add_to_cart_req_entity.g.dart';

@JsonSerializable()
class AddToCartReqEntity {

	late String goodsId;
  
  AddToCartReqEntity();

  factory AddToCartReqEntity.fromJson(Map<String, dynamic> json) => $AddToCartReqEntityFromJson(json);

  Map<String, dynamic> toJson() => $AddToCartReqEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}