import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/create_order_from_cart_req_entity.g.dart';

@JsonSerializable()
class CreateOrderFromCartReqEntity {
  late List<String> cartIdList;
  late String addressId;

  CreateOrderFromCartReqEntity();

  factory CreateOrderFromCartReqEntity.fromJson(Map<String, dynamic> json) =>
      $CreateOrderFromCartReqEntityFromJson(json);

  Map<String, dynamic> toJson() => $CreateOrderFromCartReqEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
