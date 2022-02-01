import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/create_order_from_cart_req_entity.dart';

CreateOrderFromCartReqEntity $CreateOrderFromCartReqEntityFromJson(Map<String, dynamic> json) {
  final CreateOrderFromCartReqEntity createOrderFromCartReqEntity = CreateOrderFromCartReqEntity();
  final List<String>? cartIdList = jsonConvert.convertListNotNull<String>(json['cartIdList']);
  if (cartIdList != null) {
    createOrderFromCartReqEntity.cartIdList = cartIdList;
  }
  final String? userAddrId = jsonConvert.convert<String>(json['userAddrId']);
  if (userAddrId != null) {
    createOrderFromCartReqEntity.userAddrId = userAddrId;
  }
  return createOrderFromCartReqEntity;
}

Map<String, dynamic> $CreateOrderFromCartReqEntityToJson(CreateOrderFromCartReqEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['cartIdList'] = entity.cartIdList;
  data['userAddrId'] = entity.userAddrId;
  return data;
}
