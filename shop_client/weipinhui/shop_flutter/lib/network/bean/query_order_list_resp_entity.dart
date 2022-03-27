import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_order_list_resp_entity.g.dart';

@JsonSerializable()
class QueryOrderListRespEntity {
  late int code;
  late dynamic msg;
  late List<QueryOrderListRespData> data;

  QueryOrderListRespEntity();

  factory QueryOrderListRespEntity.fromJson(Map<String, dynamic> json) => $QueryOrderListRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderListRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderListRespData {
  late String orderId;
  late int status;
  late int createTime;
  late List<QueryOrderListRespDataList> list;
  late dynamic address;

  QueryOrderListRespData();

  factory QueryOrderListRespData.fromJson(Map<String, dynamic> json) => $QueryOrderListRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderListRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderListRespDataList {
  late dynamic orderId;
  late int status;
  late String goodsId;
  late String name;
  late int price;
  late String squarePic;
  late int quantity;

  QueryOrderListRespDataList();

  factory QueryOrderListRespDataList.fromJson(Map<String, dynamic> json) => $QueryOrderListRespDataListFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderListRespDataListToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
