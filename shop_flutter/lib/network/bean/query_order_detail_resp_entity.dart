import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_order_detail_resp_entity.g.dart';

@JsonSerializable()
class QueryOrderDetailRespEntity {
  late int code;
  late dynamic msg;
  late QueryOrderDetailRespData data;

  QueryOrderDetailRespEntity();

  factory QueryOrderDetailRespEntity.fromJson(Map<String, dynamic> json) => $QueryOrderDetailRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderDetailRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderDetailRespData {
  late String orderId;
  late int status;
  late int createTime;
  late List<QueryOrderDetailRespDataList> list;
  late dynamic userAddr;

  QueryOrderDetailRespData();

  factory QueryOrderDetailRespData.fromJson(Map<String, dynamic> json) => $QueryOrderDetailRespDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderDetailRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderDetailRespDataList {
  late dynamic orderId;
  late int status;
  late String goodsId;
  late String name;
  late int price;
  late String squarePic;
  late int quantity;

  QueryOrderDetailRespDataList();

  factory QueryOrderDetailRespDataList.fromJson(Map<String, dynamic> json) =>
      $QueryOrderDetailRespDataListFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderDetailRespDataListToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
