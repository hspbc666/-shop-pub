import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_order_detail_resp_entity.dart';

QueryOrderDetailRespEntity $QueryOrderDetailRespEntityFromJson(Map<String, dynamic> json) {
  final QueryOrderDetailRespEntity queryOrderDetailRespEntity = QueryOrderDetailRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryOrderDetailRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryOrderDetailRespEntity.msg = msg;
  }
  final QueryOrderDetailRespData? data = jsonConvert.convert<QueryOrderDetailRespData>(json['data']);
  if (data != null) {
    queryOrderDetailRespEntity.data = data;
  }
  return queryOrderDetailRespEntity;
}

Map<String, dynamic> $QueryOrderDetailRespEntityToJson(QueryOrderDetailRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}

QueryOrderDetailRespData $QueryOrderDetailRespDataFromJson(Map<String, dynamic> json) {
  final QueryOrderDetailRespData queryOrderDetailRespData = QueryOrderDetailRespData();
  final String? orderId = jsonConvert.convert<String>(json['orderId']);
  if (orderId != null) {
    queryOrderDetailRespData.orderId = orderId;
  }
  final int? status = jsonConvert.convert<int>(json['status']);
  if (status != null) {
    queryOrderDetailRespData.status = status;
  }
  final int? createTime = jsonConvert.convert<int>(json['createTime']);
  if (createTime != null) {
    queryOrderDetailRespData.createTime = createTime;
  }
  final List<QueryOrderDetailRespDataList>? list =
      jsonConvert.convertListNotNull<QueryOrderDetailRespDataList>(json['list']);
  if (list != null) {
    queryOrderDetailRespData.list = list;
  }
  final dynamic? userAddr = jsonConvert.convert<dynamic>(json['userAddr']);
  if (userAddr != null) {
    queryOrderDetailRespData.userAddr = userAddr;
  }
  return queryOrderDetailRespData;
}

Map<String, dynamic> $QueryOrderDetailRespDataToJson(QueryOrderDetailRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['orderId'] = entity.orderId;
  data['status'] = entity.status;
  data['createTime'] = entity.createTime;
  data['list'] = entity.list.map((v) => v.toJson()).toList();
  data['userAddr'] = entity.userAddr;
  return data;
}

QueryOrderDetailRespDataList $QueryOrderDetailRespDataListFromJson(Map<String, dynamic> json) {
  final QueryOrderDetailRespDataList queryOrderDetailRespDataList = QueryOrderDetailRespDataList();
  final dynamic? orderId = jsonConvert.convert<dynamic>(json['orderId']);
  if (orderId != null) {
    queryOrderDetailRespDataList.orderId = orderId;
  }
  final int? status = jsonConvert.convert<int>(json['status']);
  if (status != null) {
    queryOrderDetailRespDataList.status = status;
  }
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    queryOrderDetailRespDataList.goodsId = goodsId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryOrderDetailRespDataList.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    queryOrderDetailRespDataList.price = price;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    queryOrderDetailRespDataList.squarePic = squarePic;
  }
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    queryOrderDetailRespDataList.quantity = quantity;
  }
  return queryOrderDetailRespDataList;
}

Map<String, dynamic> $QueryOrderDetailRespDataListToJson(QueryOrderDetailRespDataList entity) {
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
