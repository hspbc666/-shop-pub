import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_order_list_resp_entity.dart';

QueryOrderListRespEntity $QueryOrderListRespEntityFromJson(Map<String, dynamic> json) {
  final QueryOrderListRespEntity queryOrderListRespEntity = QueryOrderListRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryOrderListRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryOrderListRespEntity.msg = msg;
  }
  final List<QueryOrderListRespData>? data = jsonConvert.convertListNotNull<QueryOrderListRespData>(json['data']);
  if (data != null) {
    queryOrderListRespEntity.data = data;
  }
  return queryOrderListRespEntity;
}

Map<String, dynamic> $QueryOrderListRespEntityToJson(QueryOrderListRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryOrderListRespData $QueryOrderListRespDataFromJson(Map<String, dynamic> json) {
  final QueryOrderListRespData queryOrderListRespData = QueryOrderListRespData();
  final String? orderId = jsonConvert.convert<String>(json['orderId']);
  if (orderId != null) {
    queryOrderListRespData.orderId = orderId;
  }
  final int? status = jsonConvert.convert<int>(json['status']);
  if (status != null) {
    queryOrderListRespData.status = status;
  }
  final int? createTime = jsonConvert.convert<int>(json['createTime']);
  if (createTime != null) {
    queryOrderListRespData.createTime = createTime;
  }
  final List<QueryOrderListRespDataList>? list =
      jsonConvert.convertListNotNull<QueryOrderListRespDataList>(json['list']);
  if (list != null) {
    queryOrderListRespData.list = list;
  }
  final dynamic? userAddr = jsonConvert.convert<dynamic>(json['userAddr']);
  if (userAddr != null) {
    queryOrderListRespData.userAddr = userAddr;
  }
  return queryOrderListRespData;
}

Map<String, dynamic> $QueryOrderListRespDataToJson(QueryOrderListRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['orderId'] = entity.orderId;
  data['status'] = entity.status;
  data['createTime'] = entity.createTime;
  data['list'] = entity.list.map((v) => v.toJson()).toList();
  data['userAddr'] = entity.userAddr;
  return data;
}

QueryOrderListRespDataList $QueryOrderListRespDataListFromJson(Map<String, dynamic> json) {
  final QueryOrderListRespDataList queryOrderListRespDataList = QueryOrderListRespDataList();
  final dynamic? orderId = jsonConvert.convert<dynamic>(json['orderId']);
  if (orderId != null) {
    queryOrderListRespDataList.orderId = orderId;
  }
  final int? status = jsonConvert.convert<int>(json['status']);
  if (status != null) {
    queryOrderListRespDataList.status = status;
  }
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    queryOrderListRespDataList.goodsId = goodsId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryOrderListRespDataList.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    queryOrderListRespDataList.price = price;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    queryOrderListRespDataList.squarePic = squarePic;
  }
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    queryOrderListRespDataList.quantity = quantity;
  }
  return queryOrderListRespDataList;
}

Map<String, dynamic> $QueryOrderListRespDataListToJson(QueryOrderListRespDataList entity) {
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
