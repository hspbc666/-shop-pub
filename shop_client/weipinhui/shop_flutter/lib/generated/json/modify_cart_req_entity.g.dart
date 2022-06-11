import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/modify_cart_req_entity.dart';

ModifyCartReqEntity $ModifyCartReqEntityFromJson(Map<String, dynamic> json) {
  final ModifyCartReqEntity modifyCartReqEntity = ModifyCartReqEntity();
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    modifyCartReqEntity.quantity = quantity;
  }
  return modifyCartReqEntity;
}

Map<String, dynamic> $ModifyCartReqEntityToJson(ModifyCartReqEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['quantity'] = entity.quantity;
  return data;
}