import 'dart:convert';
import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_user_addr_list_resp.g.dart';

@JsonSerializable()
class QueryUserAddrListResp {
  late int code;
  late dynamic msg;
  late List<UserAddr> data;

  QueryUserAddrListResp();

  factory QueryUserAddrListResp.fromJson(Map<String, dynamic> json) => $QueryUserAddrListRespFromJson(json);

  Map<String, dynamic> toJson() => $QueryUserAddrListRespToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class UserAddr {
  String id = "";
  int userId = 0;
  late String name;
  late String phone;
  late String region;
  late String address;
  late bool defaultAddress;
  late int addrType;

  UserAddr();

  factory UserAddr.fromJson(Map<String, dynamic> json) => $UserAddrFromJson(json);

  Map<String, dynamic> toJson() => $UserAddrToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
