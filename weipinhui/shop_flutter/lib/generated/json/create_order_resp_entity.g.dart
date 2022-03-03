import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/create_order_resp_entity.dart';

CreateOrderRespEntity $CreateOrderRespEntityFromJson(Map<String, dynamic> json) {
  final CreateOrderRespEntity createOrderRespEntity = CreateOrderRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    createOrderRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    createOrderRespEntity.msg = msg;
  }
  final CreateOrderRespData? data = jsonConvert.convert<CreateOrderRespData>(json['data']);
  if (data != null) {
    createOrderRespEntity.data = data;
  }
  return createOrderRespEntity;
}

Map<String, dynamic> $CreateOrderRespEntityToJson(CreateOrderRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}

CreateOrderRespData $CreateOrderRespDataFromJson(Map<String, dynamic> json) {
  final CreateOrderRespData createOrderRespData = CreateOrderRespData();
  final String? orderId = jsonConvert.convert<String>(json['orderId']);
  if (orderId != null) {
    createOrderRespData.orderId = orderId;
  }
  return createOrderRespData;
}

Map<String, dynamic> $CreateOrderRespDataToJson(CreateOrderRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['orderId'] = entity.orderId;
  return data;
}
