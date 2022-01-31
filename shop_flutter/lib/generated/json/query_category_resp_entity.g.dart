import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_category_resp_entity.dart';

QueryCategroyResp $QueryCategroyRespEntityFromJson(Map<String, dynamic> json) {
  final QueryCategroyResp queryCategroyRespEntity = QueryCategroyResp();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryCategroyRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryCategroyRespEntity.msg = msg;
  }
  final List<QueryCategoryRespData>? data = jsonConvert.convertListNotNull<QueryCategoryRespData>(json['data']);
  if (data != null) {
    queryCategroyRespEntity.data = data;
  }
  return queryCategroyRespEntity;
}

Map<String, dynamic> $QueryCategroyRespEntityToJson(QueryCategroyResp entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryCategoryRespData $QueryCategroyRespDataFromJson(Map<String, dynamic> json) {
  final QueryCategoryRespData queryCategroyRespData = QueryCategoryRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryCategroyRespData.id = id;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryCategroyRespData.name = name;
  }
  return queryCategroyRespData;
}

Map<String, dynamic> $QueryCategroyRespDataToJson(QueryCategoryRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['name'] = entity.name;
  return data;
}
