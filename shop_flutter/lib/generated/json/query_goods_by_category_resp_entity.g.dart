import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_goods_by_category_resp_entity.dart';

QueryGoodsByCategoryRespEntity $QueryGoodsByCategoryRespEntityFromJson(Map<String, dynamic> json) {
  final QueryGoodsByCategoryRespEntity queryGoodsByCategoryRespEntity = QueryGoodsByCategoryRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryGoodsByCategoryRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryGoodsByCategoryRespEntity.msg = msg;
  }
  final List<QueryGoodsByCategoryRespData>? data =
      jsonConvert.convertListNotNull<QueryGoodsByCategoryRespData>(json['data']);
  if (data != null) {
    queryGoodsByCategoryRespEntity.data = data;
  }
  return queryGoodsByCategoryRespEntity;
}

Map<String, dynamic> $QueryGoodsByCategoryRespEntityToJson(QueryGoodsByCategoryRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryGoodsByCategoryRespData $QueryGoodsByCategoryRespDataFromJson(Map<String, dynamic> json) {
  final QueryGoodsByCategoryRespData queryGoodsByCategoryRespData = QueryGoodsByCategoryRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryGoodsByCategoryRespData.id = id;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryGoodsByCategoryRespData.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    queryGoodsByCategoryRespData.price = price;
  }
  final String? longPic = jsonConvert.convert<String>(json['longPic']);
  if (longPic != null) {
    queryGoodsByCategoryRespData.longPic = longPic;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    queryGoodsByCategoryRespData.squarePic = squarePic;
  }
  return queryGoodsByCategoryRespData;
}

Map<String, dynamic> $QueryGoodsByCategoryRespDataToJson(QueryGoodsByCategoryRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['name'] = entity.name;
  data['price'] = entity.price;
  data['longPic'] = entity.longPic;
  data['squarePic'] = entity.squarePic;
  return data;
}
