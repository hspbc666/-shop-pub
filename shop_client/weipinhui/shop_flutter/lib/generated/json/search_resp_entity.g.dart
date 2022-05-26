import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/search_resp_entity.dart';

SearchRespEntity $SearchRespEntityFromJson(Map<String, dynamic> json) {
  final SearchRespEntity searchRespEntity = SearchRespEntity();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    searchRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    searchRespEntity.msg = msg;
  }
  final List<SearchRespData>? data = jsonConvert.convertListNotNull<SearchRespData>(json['data']);
  if (data != null) {
    searchRespEntity.data = data;
  }
  return searchRespEntity;
}

Map<String, dynamic> $SearchRespEntityToJson(SearchRespEntity entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

SearchRespData $SearchRespDataFromJson(Map<String, dynamic> json) {
  final SearchRespData searchRespData = SearchRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    searchRespData.id = id;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    searchRespData.name = name;
  }
  final int? price = jsonConvert.convert<int>(json['price']);
  if (price != null) {
    searchRespData.price = price;
  }
  final String? squarePic = jsonConvert.convert<String>(json['squarePic']);
  if (squarePic != null) {
    searchRespData.squarePic = squarePic;
  }
  return searchRespData;
}

Map<String, dynamic> $SearchRespDataToJson(SearchRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['name'] = entity.name;
  data['price'] = entity.price;
  data['squarePic'] = entity.squarePic;
  return data;
}
