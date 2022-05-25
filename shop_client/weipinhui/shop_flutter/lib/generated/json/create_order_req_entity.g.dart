import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/create_order_req_entity.dart';

CreateOrderReqEntity $CreateOrderReqEntityFromJson(Map<String, dynamic> json) {
  final CreateOrderReqEntity createOrderReqEntity = CreateOrderReqEntity();
  final String? addressId = jsonConvert.convert<String>(json['addressId']);
  if (addressId != null) {
    createOrderReqEntity.addressId = addressId;
  }
  final List<CreateOrderReqSimpleCartItemList>? simpleCartItemList = jsonConvert.convertListNotNull<CreateOrderReqSimpleCartItemList>(json['simpleCartItemList']);
  if (simpleCartItemList != null) {
    createOrderReqEntity.simpleCartItemList = simpleCartItemList;
  }
  return createOrderReqEntity;
}

Map<String, dynamic> $CreateOrderReqEntityToJson(CreateOrderReqEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['addressId'] = entity.addressId;
  data['simpleCartItemList'] =  entity.simpleCartItemList.map((v) => v.toJson()).toList();
  return data;
}

CreateOrderReqSimpleCartItemList $CreateOrderReqSimpleCartItemListFromJson(Map<String, dynamic> json) {
  final CreateOrderReqSimpleCartItemList createOrderReqSimpleCartItemList = CreateOrderReqSimpleCartItemList();
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    createOrderReqSimpleCartItemList.goodsId = goodsId;
  }
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    createOrderReqSimpleCartItemList.quantity = quantity;
  }
  return createOrderReqSimpleCartItemList;
}

Map<String, dynamic> $CreateOrderReqSimpleCartItemListToJson(CreateOrderReqSimpleCartItemList entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['goodsId'] = entity.goodsId;
  data['quantity'] = entity.quantity;
  return data;
}