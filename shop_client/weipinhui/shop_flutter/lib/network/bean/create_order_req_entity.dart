import 'dart:convert';
import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/create_order_req_entity.g.dart';

@JsonSerializable()
class CreateOrderReqEntity {

	late String addressId;
	late List<CreateOrderReqSimpleCartItemList> simpleCartItemList;
  
  CreateOrderReqEntity();

  factory CreateOrderReqEntity.fromJson(Map<String, dynamic> json) => $CreateOrderReqEntityFromJson(json);

  Map<String, dynamic> toJson() => $CreateOrderReqEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class CreateOrderReqSimpleCartItemList {

	late String goodsId;
	late int quantity;
  
  CreateOrderReqSimpleCartItemList();

  factory CreateOrderReqSimpleCartItemList.fromJson(Map<String, dynamic> json) => $CreateOrderReqSimpleCartItemListFromJson(json);

  Map<String, dynamic> toJson() => $CreateOrderReqSimpleCartItemListToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}