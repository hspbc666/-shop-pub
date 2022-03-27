import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_default_addr_resp_entity.g.dart';

@JsonSerializable()
class QueryDefaultAddrRespEntity {
  late int code;
  late dynamic msg;
  late QueryDefaultAddrRespData data;

  QueryDefaultAddrRespEntity();

  factory QueryDefaultAddrRespEntity.fromJson(Map<String, dynamic> json) => $QueryDefaultAddrRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryDefaultAddrRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryDefaultAddrRespData {
  late String id;
  late int userId;
  late String name;
  late String phone;
  late String region;
  late String address;
  late bool defaultAddress;
  late int addrType;

  QueryDefaultAddrRespData();

  factory QueryDefaultAddrRespData.fromJson(Map<String, dynamic> json) => $QueryDefaultAddrRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryDefaultAddrRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
