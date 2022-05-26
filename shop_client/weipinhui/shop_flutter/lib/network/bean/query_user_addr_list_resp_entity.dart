import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_user_addr_list_resp_entity.g.dart';

@JsonSerializable()
class QueryAddressListRespEntity {
  late int code;
  late dynamic msg;
  late List<QueryAddressListRespData> data;

  QueryAddressListRespEntity();

  factory QueryAddressListRespEntity.fromJson(Map<String, dynamic> json) => $QueryAddressListRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryAddressListRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryAddressListRespData {
  late String id;
  late int userId;
  late String name;
  late String phone;
  late String region;
  late String address;
  late bool defaultAddress;

  QueryAddressListRespData();

  factory QueryAddressListRespData.fromJson(Map<String, dynamic> json) => $QueryAddressListRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryAddressListRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
