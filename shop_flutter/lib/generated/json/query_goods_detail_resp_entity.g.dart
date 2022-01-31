import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_goods_detail_resp_entity.dart';

QueryGoodsDetailRespEntity $QueryGoodsDetailRespEntityFromJson(Map<String, dynamic> json) {
  final QueryGoodsDetailRespEntity queryGoodsDetailRespEntity = QueryGoodsDetailRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryGoodsDetailRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryGoodsDetailRespEntity.msg = msg;
  }
  final QueryGoodsDetailRespData? data = jsonConvert.convert<QueryGoodsDetailRespData>(json['data']);
  if (data != null) {
    queryGoodsDetailRespEntity.data = data;
  }
  return queryGoodsDetailRespEntity;
}

Map<String, dynamic> $QueryGoodsDetailRespEntityToJson(QueryGoodsDetailRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.toJson();
  return data;
}

QueryGoodsDetailRespData $QueryGoodsDetailRespDataFromJson(Map<String, dynamic> json) {
  final QueryGoodsDetailRespData queryGoodsDetailRespData = QueryGoodsDetailRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryGoodsDetailRespData.id = id;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryGoodsDetailRespData.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    queryGoodsDetailRespData.price = price;
  }
  final String? longPic = jsonConvert.convert<String>(json['longPic']);
  if (longPic != null) {
    queryGoodsDetailRespData.longPic = longPic;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    queryGoodsDetailRespData.squarePic = squarePic;
  }
  return queryGoodsDetailRespData;
}

Map<String, dynamic> $QueryGoodsDetailRespDataToJson(QueryGoodsDetailRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['name'] = entity.name;
  data['price'] = entity.price;
  data['longPic'] = entity.longPic;
  data['squarePic'] = entity.squarePic;
  return data;
}
