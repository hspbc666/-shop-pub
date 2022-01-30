import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/query_order_detail_entity.g.dart';

@JsonSerializable()
class QueryOrderDetailResp {
  late int code;
  late dynamic msg;
  late QueryOrderDetailData data;

  QueryOrderDetailResp();

  factory QueryOrderDetailResp.fromJson(Map<String, dynamic> json) => $QueryOrderDetailEntityFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderDetailEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderDetailData {
  late String orderId;
  late int status;
  late int createTime;
  late List<QueryOrderDetailDataItem> list;
  late dynamic userAddr;

  QueryOrderDetailData();

  factory QueryOrderDetailData.fromJson(Map<String, dynamic> json) => $QueryOrderDetailDataFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderDetailDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class QueryOrderDetailDataItem {
  late dynamic orderId;
  late int status;
  late String goodsId;
  late String name;
  late int price;
  late String squarePic;
  late int quantity;

  QueryOrderDetailDataItem();

  factory QueryOrderDetailDataItem.fromJson(Map<String, dynamic> json) => $QueryOrderDetailDataListFromJson(json);

  Map<String, dynamic> toJson() => $QueryOrderDetailDataListToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
