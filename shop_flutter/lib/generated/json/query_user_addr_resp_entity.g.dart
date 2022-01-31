import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_user_addr_resp_entity.dart';

QueryUserAddrRespEntity $QueryUserAddrRespEntityFromJson(Map<String, dynamic> json) {
  final QueryUserAddrRespEntity queryUserAddrRespEntity = QueryUserAddrRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryUserAddrRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryUserAddrRespEntity.msg = msg;
  }
  final QueryUserAddrRespData? data = jsonConvert.convert<QueryUserAddrRespData>(json['data']);
  if (data != null) {
    queryUserAddrRespEntity.data = data;
  }
  return queryUserAddrRespEntity;
}

Map<String, dynamic> $QueryUserAddrRespEntityToJson(QueryUserAddrRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}

QueryUserAddrRespData $QueryUserAddrRespDataFromJson(Map<String, dynamic> json) {
  final QueryUserAddrRespData queryUserAddrRespData = QueryUserAddrRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryUserAddrRespData.id = id;
  }
  final int? userId = jsonConvert.convert<int>(json['userId']);
  if (userId != null) {
    queryUserAddrRespData.userId = userId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryUserAddrRespData.name = name;
  }
  final String? phone = jsonConvert.convert<String>(json['phone']);
  if (phone != null) {
    queryUserAddrRespData.phone = phone;
  }
  final String? region = jsonConvert.convert<String>(json['region']);
  if (region != null) {
    queryUserAddrRespData.region = region;
  }
  final String? address = jsonConvert.convert<String>(json['address']);
  if (address != null) {
    queryUserAddrRespData.address = address;
  }
  final bool? defaultAddress = jsonConvert.convert<bool>(json['defaultAddress']);
  if (defaultAddress != null) {
    queryUserAddrRespData.defaultAddress = defaultAddress;
  }
  final int? addrType = jsonConvert.convert<int>(json['addrType']);
  if (addrType != null) {
    queryUserAddrRespData.addrType = addrType;
  }
  return queryUserAddrRespData;
}

Map<String, dynamic> $QueryUserAddrRespDataToJson(QueryUserAddrRespData entity) {
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
