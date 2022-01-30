import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_user_addr_list_resp.dart';
import 'package:shop_flutter/network/bean/query_user_addr_resp.dart';

QueryUserAddrResp $QueryUserAddrRespFromJson(Map<String, dynamic> json) {
  final QueryUserAddrResp queryUserAddrResp = QueryUserAddrResp();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryUserAddrResp.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryUserAddrResp.msg = msg;
  }
  final UserAddr? data = jsonConvert.convert<UserAddr>(json['data']);
  if (data != null) {
    queryUserAddrResp.data = data;
  }
  return queryUserAddrResp;
}

Map<String, dynamic> $QueryUserAddrRespToJson(QueryUserAddrResp entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}
