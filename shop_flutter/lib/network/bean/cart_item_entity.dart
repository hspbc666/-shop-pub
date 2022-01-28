import 'dart:convert';
import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/cart_item_entity.g.dart';

@JsonSerializable()
class CartItemEntity {

	late int code;
	late dynamic msg;
	late List<CartItemData> data;
  
  CartItemEntity();

  factory CartItemEntity.fromJson(Map<String, dynamic> json) => $CartItemEntityFromJson(json);

  Map<String, dynamic> toJson() => $CartItemEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class CartItemData {

	late String id;
	late String goodsId;
	late String name;
	late int price;
	late String longPic;
	late String squarePic;
	late int quantity;
  
  CartItemData();

  factory CartItemData.fromJson(Map<String, dynamic> json) => $CartItemDataFromJson(json);

  Map<String, dynamic> toJson() => $CartItemDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}