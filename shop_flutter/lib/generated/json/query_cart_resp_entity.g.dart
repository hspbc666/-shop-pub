import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_cart_resp_entity.dart';

QueryCartRespEntity $QueryCartRespEntityFromJson(Map<String, dynamic> json) {
  final QueryCartRespEntity queryCartRespEntity = QueryCartRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryCartRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryCartRespEntity.msg = msg;
  }
  final List<QueryCartRespData>? data = jsonConvert.convertListNotNull<QueryCartRespData>(json['data']);
  if (data != null) {
    queryCartRespEntity.data = data;
  }
  return queryCartRespEntity;
}

Map<String, dynamic> $QueryCartRespEntityToJson(QueryCartRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryCartRespData $QueryCartRespDataFromJson(Map<String, dynamic> json) {
  final QueryCartRespData queryCartRespData = QueryCartRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryCartRespData.id = id;
  }
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    queryCartRespData.goodsId = goodsId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryCartRespData.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    queryCartRespData.price = price;
  }
  final String? longPic = jsonConvert.convert<String>(json['longPic']);
  if (longPic != null) {
    queryCartRespData.longPic = longPic;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    queryCartRespData.squarePic = squarePic;
  }
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    queryCartRespData.quantity = quantity;
  }
  return queryCartRespData;
}

Map<String, dynamic> $QueryCartRespDataToJson(QueryCartRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['goodsId'] = entity.goodsId;
  data['name'] = entity.name;
  data['price'] = entity.price;
  data['longPic'] = entity.longPic;
  data['squarePic'] = entity.squarePic;
  data['quantity'] = entity.quantity;
  return data;
}
