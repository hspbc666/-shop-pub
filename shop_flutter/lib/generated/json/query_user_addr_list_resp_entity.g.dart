import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_user_addr_list_resp_entity.dart';

QueryUserAddrListRespEntity $QueryUserAddrListRespEntityFromJson(Map<String, dynamic> json) {
  final QueryUserAddrListRespEntity queryUserAddrListRespEntity = QueryUserAddrListRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryUserAddrListRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryUserAddrListRespEntity.msg = msg;
  }
  final List<QueryUserAddrListRespData>? data = jsonConvert.convertListNotNull<QueryUserAddrListRespData>(json['data']);
  if (data != null) {
    queryUserAddrListRespEntity.data = data;
  }
  return queryUserAddrListRespEntity;
}

Map<String, dynamic> $QueryUserAddrListRespEntityToJson(QueryUserAddrListRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryUserAddrListRespData $QueryUserAddrListRespDataFromJson(Map<String, dynamic> json) {
  final QueryUserAddrListRespData queryUserAddrListRespData = QueryUserAddrListRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryUserAddrListRespData.id = id;
  }
  final int? userId = jsonConvert.convert<int>(json['userId']);
  if (userId != null) {
    queryUserAddrListRespData.userId = userId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryUserAddrListRespData.name = name;
  }
  final String? phone = jsonConvert.convert<String>(json['phone']);
  if (phone != null) {
    queryUserAddrListRespData.phone = phone;
  }
  final String? region = jsonConvert.convert<String>(json['region']);
  if (region != null) {
    queryUserAddrListRespData.region = region;
  }
  final String? address = jsonConvert.convert<String>(json['address']);
  if (address != null) {
    queryUserAddrListRespData.address = address;
  }
  final bool? defaultAddress = jsonConvert.convert<bool>(json['defaultAddress']);
  if (defaultAddress != null) {
    queryUserAddrListRespData.defaultAddress = defaultAddress;
  }
  final int? addrType = jsonConvert.convert<int>(json['addrType']);
  if (addrType != null) {
    queryUserAddrListRespData.addrType = addrType;
  }
  return queryUserAddrListRespData;
}

Map<String, dynamic> $QueryUserAddrListRespDataToJson(QueryUserAddrListRespData entity) {
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
