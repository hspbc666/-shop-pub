import 'dart:convert';
import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_user_addr_resp_entity.g.dart';
import 'package:shop_flutter/network/bean/query_user_addr_list_resp.dart';

@JsonSerializable()
class QueryUserAddrResp {
  late int code;
  late dynamic msg;
  late UserAddr data;

  QueryUserAddrResp();

  factory QueryUserAddrResp.fromJson(Map<String, dynamic> json) => $QueryUserAddrRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryUserAddrRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
