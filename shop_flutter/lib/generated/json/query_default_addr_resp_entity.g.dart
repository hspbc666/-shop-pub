import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_default_addr_resp_entity.dart';

QueryDefaultAddrRespEntity $QueryDefaultAddrRespEntityFromJson(Map<String, dynamic> json) {
  final QueryDefaultAddrRespEntity queryDefaultAddrRespEntity = QueryDefaultAddrRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryDefaultAddrRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryDefaultAddrRespEntity.msg = msg;
  }
  final QueryDefaultAddrRespData? data = jsonConvert.convert<QueryDefaultAddrRespData>(json['data']);
  if (data != null) {
    queryDefaultAddrRespEntity.data = data;
  }
  return queryDefaultAddrRespEntity;
}

Map<String, dynamic> $QueryDefaultAddrRespEntityToJson(QueryDefaultAddrRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}

QueryDefaultAddrRespData $QueryDefaultAddrRespDataFromJson(Map<String, dynamic> json) {
  final QueryDefaultAddrRespData queryDefaultAddrRespData = QueryDefaultAddrRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryDefaultAddrRespData.id = id;
  }
  final int? userId = jsonConvert.convert<int>(json['userId']);
  if (userId != null) {
    queryDefaultAddrRespData.userId = userId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryDefaultAddrRespData.name = name;
  }
  final String? phone = jsonConvert.convert<String>(json['phone']);
  if (phone != null) {
    queryDefaultAddrRespData.phone = phone;
  }
  final String? region = jsonConvert.convert<String>(json['region']);
  if (region != null) {
    queryDefaultAddrRespData.region = region;
  }
  final String? address = jsonConvert.convert<String>(json['address']);
  if (address != null) {
    queryDefaultAddrRespData.address = address;
  }
  final bool? defaultAddress = jsonConvert.convert<bool>(json['defaultAddress']);
  if (defaultAddress != null) {
    queryDefaultAddrRespData.defaultAddress = defaultAddress;
  }
  final int? addrType = jsonConvert.convert<int>(json['addrType']);
  if (addrType != null) {
    queryDefaultAddrRespData.addrType = addrType;
  }
  return queryDefaultAddrRespData;
}

Map<String, dynamic> $QueryDefaultAddrRespDataToJson(QueryDefaultAddrRespData entity) {
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
