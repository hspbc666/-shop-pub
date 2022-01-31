import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_order_detail_resp_entity.dart';

QueryOrderDetailResp $QueryOrderDetailEntityFromJson(Map<String, dynamic> json) {
  final QueryOrderDetailResp queryOrderDetailEntity = QueryOrderDetailResp();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryOrderDetailEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryOrderDetailEntity.msg = msg;
  }
  final QueryOrderDetailData? data = jsonConvert.convert<QueryOrderDetailData>(json['data']);
  if (data != null) {
    queryOrderDetailEntity.data = data;
  }
  return queryOrderDetailEntity;
}

Map<String, dynamic> $QueryOrderDetailEntityToJson(QueryOrderDetailResp entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}

QueryOrderDetailData $QueryOrderDetailDataFromJson(Map<String, dynamic> json) {
  final QueryOrderDetailData queryOrderDetailData = QueryOrderDetailData();
  final String? orderId = jsonConvert.convert<String>(json['orderId']);
  if (orderId != null) {
    queryOrderDetailData.orderId = orderId;
  }
  final int? status = jsonConvert.convert<int>(json['status']);
  if (status != null) {
    queryOrderDetailData.status = status;
  }
  final int? createTime = jsonConvert.convert<int>(json['createTime']);
  if (createTime != null) {
    queryOrderDetailData.createTime = createTime;
  }
  final List<QueryOrderDetailDataItem>? list = jsonConvert.convertListNotNull<QueryOrderDetailDataItem>(json['list']);
  if (list != null) {
    queryOrderDetailData.list = list;
  }
  final dynamic? userAddr = jsonConvert.convert<dynamic>(json['userAddr']);
  if (userAddr != null) {
    queryOrderDetailData.userAddr = userAddr;
  }
  return queryOrderDetailData;
}

Map<String, dynamic> $QueryOrderDetailDataToJson(QueryOrderDetailData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['orderId'] = entity.orderId;
  data['status'] = entity.status;
  data['createTime'] = entity.createTime;
  data['list'] = entity.list.map((v) => v.toJson()).toList();
  data['userAddr'] = entity.userAddr;
  return data;
}

QueryOrderDetailDataItem $QueryOrderDetailDataListFromJson(Map<String, dynamic> json) {
  final QueryOrderDetailDataItem queryOrderDetailDataList = QueryOrderDetailDataItem();
  final dynamic? orderId = jsonConvert.convert<dynamic>(json['orderId']);
  if (orderId != null) {
    queryOrderDetailDataList.orderId = orderId;
  }
  final int? status = jsonConvert.convert<int>(json['status']);
  if (status != null) {
    queryOrderDetailDataList.status = status;
  }
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    queryOrderDetailDataList.goodsId = goodsId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryOrderDetailDataList.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    queryOrderDetailDataList.price = price;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    queryOrderDetailDataList.squarePic = squarePic;
  }
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    queryOrderDetailDataList.quantity = quantity;
  }
  return queryOrderDetailDataList;
}

Map<String, dynamic> $QueryOrderDetailDataListToJson(QueryOrderDetailDataItem entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['orderId'] = entity.orderId;
  data['status'] = entity.status;
  data['goodsId'] = entity.goodsId;
  data['name'] = entity.name;
  data['price'] = entity.price;
  data['squarePic'] = entity.squarePic;
  data['quantity'] = entity.quantity;
  return data;
}
