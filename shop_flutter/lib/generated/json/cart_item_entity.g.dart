import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/cart_item_entity.dart';

CartItemEntity $CartItemEntityFromJson(Map<String, dynamic> json) {
	final CartItemEntity cartItemEntity = CartItemEntity();
	final int? code = jsonConvert.convert<int>(json['code']);
	if (code != null) {
		cartItemEntity.code = code;
	}
	final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
	if (msg != null) {
		cartItemEntity.msg = msg;
	}
	final List<CartItemData>? data = jsonConvert.convertListNotNull<CartItemData>(json['data']);
	if (data != null) {
		cartItemEntity.data = data;
	}
	return cartItemEntity;
}

Map<String, dynamic> $CartItemEntityToJson(CartItemEntity entity) {
	final Map<String, dynamic> data = <String, dynamic>{};
	data['code'] = entity.code;
	data['msg'] = entity.msg;
	data['data'] =  entity.data.map((v) => v.toJson()).toList();
	return data;
}

CartItemData $CartItemDataFromJson(Map<String, dynamic> json) {
	final CartItemData cartItemData = CartItemData();
	final String? id = jsonConvert.convert<String>(json['id']);
	if (id != null) {
		cartItemData.id = id;
	}
	final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
	if (goodsId != null) {
		cartItemData.goodsId = goodsId;
	}
	final String? name = jsonConvert.convert<String>(json['name']);
	if (name != null) {
		cartItemData.name = name;
	}
	final int? price = jsonConvert.convert<int>(json['price']);
	if (price != null) {
		cartItemData.price = price;
	}
	final String? longPic = jsonConvert.convert<String>(json['longPic']);
	if (longPic != null) {
		cartItemData.longPic = longPic;
	}
	final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
	if (squarePic != null) {
		cartItemData.squarePic = squarePic;
	}
	final int? quantity = jsonConvert.convert<int>(json['quantity']);
	if (quantity != null) {
		cartItemData.quantity = quantity;
	}
	return cartItemData;
}

Map<String, dynamic> $CartItemDataToJson(CartItemData entity) {
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