import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_cart_resp.dart';

QueryCartResp $QueryCartRespFromJson(Map<String, dynamic> json) {
  final QueryCartResp queryCartResp = QueryCartResp();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryCartResp.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryCartResp.msg = msg;
  }
  final List<CartItem>? data = jsonConvert.convertListNotNull<CartItem>(json['data']);
  if (data != null) {
    queryCartResp.data = data;
  }
  return queryCartResp;
}

Map<String, dynamic> $QueryCartRespToJson(QueryCartResp entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

CartItem $CartItemFromJson(Map<String, dynamic> json) {
  final CartItem cartItem = CartItem();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    cartItem.id = id;
  }
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    cartItem.goodsId = goodsId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    cartItem.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    cartItem.price = price;
  }
  final String? longPic = jsonConvert.convert<String>(json['longPic']);
  if (longPic != null) {
    cartItem.longPic = longPic;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    cartItem.squarePic = squarePic;
  }
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    cartItem.quantity = quantity;
  }
  return cartItem;
}

Map<String, dynamic> $CartItemToJson(CartItem entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['goodsId'] = entity.goodsId;
  data['name'] = entity.name;
  data['price'] = entity.price;
  data['longPic'] = entity.longPic;
  data['squarePic'] = entity.squarePic;
  data['quantity'] = entity.quantity;
  return data;
}
