import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_order_list_resp_resp.dart';

QueryOrderListResp $QueryOrderListRespEntityFromJson(Map<String, dynamic> json) {
  final QueryOrderListResp queryOrderListRespEntity = QueryOrderListResp();
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

Map<String, dynamic> $QueryOrderListRespEntityToJson(QueryOrderListResp entity) {
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
  final List<QueryOrderListRespDataItem>? list =
      jsonConvert.convertListNotNull<QueryOrderListRespDataItem>(json['list']);
  if (list != null) {
    queryOrderListRespData.list = list;
  }
  final QueryOrderListRespDataUserAddr? userAddr =
      jsonConvert.convert<QueryOrderListRespDataUserAddr>(json['userAddr']);
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
  data['userAddr'] = entity.userAddr.toJson();
  return data;
}

QueryOrderListRespDataItem $QueryOrderListRespDataListFromJson(Map<String, dynamic> json) {
  final QueryOrderListRespDataItem queryOrderListRespDataList = QueryOrderListRespDataItem();
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

Map<String, dynamic> $QueryOrderListRespDataListToJson(QueryOrderListRespDataItem entity) {
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

QueryOrderListRespDataUserAddr $QueryOrderListRespDataUserAddrFromJson(Map<String, dynamic> json) {
  final QueryOrderListRespDataUserAddr queryOrderListRespDataUserAddr = QueryOrderListRespDataUserAddr();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryOrderListRespDataUserAddr.id = id;
  }
  final int? userId = jsonConvert.convert<int>(json['userId']);
  if (userId != null) {
    queryOrderListRespDataUserAddr.userId = userId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryOrderListRespDataUserAddr.name = name;
  }
  final String? phone = jsonConvert.convert<String>(json['phone']);
  if (phone != null) {
    queryOrderListRespDataUserAddr.phone = phone;
  }
  final String? region = jsonConvert.convert<String>(json['region']);
  if (region != null) {
    queryOrderListRespDataUserAddr.region = region;
  }
  final String? address = jsonConvert.convert<String>(json['address']);
  if (address != null) {
    queryOrderListRespDataUserAddr.address = address;
  }
  final bool? defaultAddress = jsonConvert.convert<bool>(json['defaultAddress']);
  if (defaultAddress != null) {
    queryOrderListRespDataUserAddr.defaultAddress = defaultAddress;
  }
  final int? addrType = jsonConvert.convert<int>(json['addrType']);
  if (addrType != null) {
    queryOrderListRespDataUserAddr.addrType = addrType;
  }
  return queryOrderListRespDataUserAddr;
}

Map<String, dynamic> $QueryOrderListRespDataUserAddrToJson(QueryOrderListRespDataUserAddr entity) {
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
