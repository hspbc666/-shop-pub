import 'dart:convert';
import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/modify_cart_req_entity.g.dart';

@JsonSerializable()
class ModifyCartReqEntity {

	late int quantity;
  
  ModifyCartReqEntity();

  factory ModifyCartReqEntity.fromJson(Map<String, dynamic> json) => $ModifyCartReqEntityFromJson(json);

  Map<String, dynamic> toJson() => $ModifyCartReqEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}