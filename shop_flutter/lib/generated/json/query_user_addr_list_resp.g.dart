import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_user_addr_list_resp.dart';

QueryUserAddrListResp $QueryUserAddrListRespFromJson(Map<String, dynamic> json) {
  final QueryUserAddrListResp queryUserAddrListResp = QueryUserAddrListResp();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryUserAddrListResp.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryUserAddrListResp.msg = msg;
  }
  final List<UserAddr>? data = jsonConvert.convertListNotNull<UserAddr>(json['data']);
  if (data != null) {
    queryUserAddrListResp.data = data;
  }
  return queryUserAddrListResp;
}

Map<String, dynamic> $QueryUserAddrListRespToJson(QueryUserAddrListResp entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

UserAddr $UserAddrFromJson(Map<String, dynamic> json) {
  final UserAddr userAddr = UserAddr();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    userAddr.id = id;
  }
  final int? userId = jsonConvert.convert<int>(json['userId']);
  if (userId != null) {
    userAddr.userId = userId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    userAddr.name = name;
  }
  final String? phone = jsonConvert.convert<String>(json['phone']);
  if (phone != null) {
    userAddr.phone = phone;
  }
  final String? region = jsonConvert.convert<String>(json['region']);
  if (region != null) {
    userAddr.region = region;
  }
  final String? address = jsonConvert.convert<String>(json['address']);
  if (address != null) {
    userAddr.address = address;
  }
  final bool? defaultAddress = jsonConvert.convert<bool>(json['defaultAddress']);
  if (defaultAddress != null) {
    userAddr.defaultAddress = defaultAddress;
  }
  final int? addrType = jsonConvert.convert<int>(json['addrType']);
  if (addrType != null) {
    userAddr.addrType = addrType;
  }
  return userAddr;
}

Map<String, dynamic> $UserAddrToJson(UserAddr entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['userId'] = entity.userId;
  data['name'] = entity.name;
  data['phone'] = entity.phone;
  data['region'] = entity.region;
  data['address'] = entity.address;
  data['defaultAddress'] = entity.defaultAddress;
  data['addrType'] = entity.addrType;
  return data;
}
