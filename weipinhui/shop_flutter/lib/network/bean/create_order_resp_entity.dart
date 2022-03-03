import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/create_order_resp_entity.g.dart';

@JsonSerializable()
class CreateOrderRespEntity {
  late int code;
  late dynamic msg;
  late CreateOrderRespData data;

  CreateOrderRespEntity();

  factory CreateOrderRespEntity.fromJson(Map<String, dynamic> json) => $CreateOrderRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $CreateOrderRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class CreateOrderRespData {
  late String orderId;

  CreateOrderRespData();

  factory CreateOrderRespData.fromJson(Map<String, dynamic> json) => $CreateOrderRespDataFromJson(json);

  Map<String, dynamic> toJson() => $CreateOrderRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
