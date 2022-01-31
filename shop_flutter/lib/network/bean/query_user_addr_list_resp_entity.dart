import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_user_addr_list_resp_entity.g.dart';

@JsonSerializable()
class QueryUserAddrListRespEntity {
  late int code;
  late dynamic msg;
  late List<QueryUserAddrListRespData> data;

  QueryUserAddrListRespEntity();

  factory QueryUserAddrListRespEntity.fromJson(Map<String, dynamic> json) => $QueryUserAddrListRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryUserAddrListRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryUserAddrListRespData {
  late String id;
  late int userId;
  late String name;
  late String phone;
  late String region;
  late String address;
  late bool defaultAddress;
  late int addrType;

  QueryUserAddrListRespData();

  factory QueryUserAddrListRespData.fromJson(Map<String, dynamic> json) => $QueryUserAddrListRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryUserAddrListRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
