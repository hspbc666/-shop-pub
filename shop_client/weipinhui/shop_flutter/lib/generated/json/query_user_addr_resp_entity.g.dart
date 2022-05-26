import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_user_addr_resp_entity.dart';

QueryAddressRespEntity $QueryAddressRespEntityFromJson(Map<String, dynamic> json) {
  final QueryAddressRespEntity queryAddressRespEntity = QueryAddressRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryAddressRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryAddressRespEntity.msg = msg;
  }
  final QueryAddressRespData? data = jsonConvert.convert<QueryAddressRespData>(json['data']);
  if (data != null) {
    queryAddressRespEntity.data = data;
  }
  return queryAddressRespEntity;
}

Map<String, dynamic> $QueryAddressRespEntityToJson(QueryAddressRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}

QueryAddressRespData $QueryAddressRespDataFromJson(Map<String, dynamic> json) {
  final QueryAddressRespData queryAddressRespData = QueryAddressRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryAddressRespData.id = id;
  }
  final int? userId = jsonConvert.convert<int>(json['userId']);
  if (userId != null) {
    queryAddressRespData.userId = userId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryAddressRespData.name = name;
  }
  final String? phone = jsonConvert.convert<String>(json['phone']);
  if (phone != null) {
    queryAddressRespData.phone = phone;
  }
  final String? region = jsonConvert.convert<String>(json['region']);
  if (region != null) {
    queryAddressRespData.region = region;
  }
  final String? address = jsonConvert.convert<String>(json['address']);
  if (address != null) {
    queryAddressRespData.address = address;
  }
  final bool? defaultAddress = jsonConvert.convert<bool>(json['defaultAddress']);
  if (defaultAddress != null) {
    queryAddressRespData.defaultAddress = defaultAddress;
  }
  return queryAddressRespData;
}

Map<String, dynamic> $QueryAddressRespDataToJson(QueryAddressRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['userId'] = entity.userId;
  data['name'] = entity.name;
  data['phone'] = entity.phone;
  data['region'] = entity.region;
  data['address'] = entity.address;
  data['defaultAddress'] = entity.defaultAddress;
  return data;
}
