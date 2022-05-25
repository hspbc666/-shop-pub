import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/add_to_cart_req_entity.dart';

AddToCartReqEntity $AddToCartReqEntityFromJson(Map<String, dynamic> json) {
  final AddToCartReqEntity addToCartReqEntity = AddToCartReqEntity();
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    addToCartReqEntity.goodsId = goodsId;
  }
  return addToCartReqEntity;
}

Map<String, dynamic> $AddToCartReqEntityToJson(AddToCartReqEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['goodsId'] = entity.goodsId;
  return data;
}