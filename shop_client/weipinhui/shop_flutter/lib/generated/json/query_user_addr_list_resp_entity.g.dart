import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_user_addr_list_resp_entity.dart';

QueryAddressListRespEntity $QueryAddressListRespEntityFromJson(Map<String, dynamic> json) {
  final QueryAddressListRespEntity queryAddressListRespEntity = QueryAddressListRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryAddressListRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryAddressListRespEntity.msg = msg;
  }
  final List<QueryAddressListRespData>? data = jsonConvert.convertListNotNull<QueryAddressListRespData>(json['data']);
  if (data != null) {
    queryAddressListRespEntity.data = data;
  }
  return queryAddressListRespEntity;
}

Map<String, dynamic> $QueryAddressListRespEntityToJson(QueryAddressListRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryAddressListRespData $QueryAddressListRespDataFromJson(Map<String, dynamic> json) {
  final QueryAddressListRespData queryAddressListRespData = QueryAddressListRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryAddressListRespData.id = id;
  }
  final int? userId = jsonConvert.convert<int>(json['userId']);
  if (userId != null) {
    queryAddressListRespData.userId = userId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryAddressListRespData.name = name;
  }
  final String? phone = jsonConvert.convert<String>(json['phone']);
  if (phone != null) {
    queryAddressListRespData.phone = phone;
  }
  final String? region = jsonConvert.convert<String>(json['region']);
  if (region != null) {
    queryAddressListRespData.region = region;
  }
  final String? address = jsonConvert.convert<String>(json['address']);
  if (address != null) {
    queryAddressListRespData.address = address;
  }
  final bool? defaultAddress = jsonConvert.convert<bool>(json['defaultAddress']);
  if (defaultAddress != null) {
    queryAddressListRespData.defaultAddress = defaultAddress;
  }
  final int? addrType = jsonConvert.convert<int>(json['addrType']);
  if (addrType != null) {
    queryAddressListRespData.addrType = addrType;
  }
  return queryAddressListRespData;
}

Map<String, dynamic> $QueryAddressListRespDataToJson(QueryAddressListRespData entity) {
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
