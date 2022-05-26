import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_cart_goods_resp_entity.dart';

QueryCartGoodsRespEntity $QueryCartGoodsRespEntityFromJson(Map<String, dynamic> json) {
  final QueryCartGoodsRespEntity queryCartGoodsRespEntity = QueryCartGoodsRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryCartGoodsRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryCartGoodsRespEntity.msg = msg;
  }
  final List<QueryCartGoodsRespData>? data = jsonConvert.convertListNotNull<QueryCartGoodsRespData>(json['data']);
  if (data != null) {
    queryCartGoodsRespEntity.data = data;
  }
  return queryCartGoodsRespEntity;
}

Map<String, dynamic> $QueryCartGoodsRespEntityToJson(QueryCartGoodsRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryCartGoodsRespData $QueryCartGoodsRespDataFromJson(Map<String, dynamic> json) {
  final QueryCartGoodsRespData queryCartGoodsRespData = QueryCartGoodsRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryCartGoodsRespData.id = id;
  }
  final String? goodsId = jsonConvert.convert<String>(json['goodsId']);
  if (goodsId != null) {
    queryCartGoodsRespData.goodsId = goodsId;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryCartGoodsRespData.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    queryCartGoodsRespData.price = price;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    queryCartGoodsRespData.squarePic = squarePic;
  }
  final int? quantity = jsonConvert.convert<int>(json['quantity']);
  if (quantity != null) {
    queryCartGoodsRespData.quantity = quantity;
  }
  return queryCartGoodsRespData;
}

Map<String, dynamic> $QueryCartGoodsRespDataToJson(QueryCartGoodsRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['goodsId'] = entity.goodsId;
  data['name'] = entity.name;
  data['price'] = entity.price;
  data['squarePic'] = entity.squarePic;
  data['quantity'] = entity.quantity;
  return data;
}
