import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_user_addr_resp_entity.g.dart';

@JsonSerializable()
class QueryAddressRespEntity {
  late int code;
  late dynamic msg;
  late QueryAddressRespData data;

  QueryAddressRespEntity();

  factory QueryAddressRespEntity.fromJson(Map<String, dynamic> json) => $QueryAddressRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryAddressRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryAddressRespData {
  late String id;
  late int userId;
  late String name;
  late String phone;
  late String region;
  late String address;
  late bool defaultAddress;
  late int addrType;

  QueryAddressRespData();

  factory QueryAddressRespData.fromJson(Map<String, dynamic> json) => $QueryAddressRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryAddressRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
