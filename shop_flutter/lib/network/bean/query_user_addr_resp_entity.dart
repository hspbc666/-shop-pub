import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_user_addr_resp_entity.g.dart';

@JsonSerializable()
class QueryUserAddrRespEntity {
  late int code;
  late dynamic msg;
  late QueryUserAddrRespData data;

  QueryUserAddrRespEntity();

  factory QueryUserAddrRespEntity.fromJson(Map<String, dynamic> json) => $QueryUserAddrRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryUserAddrRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryUserAddrRespData {
  late String id;
  late int userId;
  late String name;
  late String phone;
  late String region;
  late String address;
  late bool defaultAddress;
  late int addrType;

  QueryUserAddrRespData();

  factory QueryUserAddrRespData.fromJson(Map<String, dynamic> json) => $QueryUserAddrRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryUserAddrRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
