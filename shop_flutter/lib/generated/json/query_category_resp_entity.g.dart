import 'package:shop_flutter/generated/json/base/json_convert_content.dart';
import 'package:shop_flutter/network/bean/query_category_resp_entity.dart';

QueryCategoryResp $QueryCategoryRespEntityFromJson(Map<String, dynamic> json) {
  final QueryCategoryResp queryCategoryRespEntity = QueryCategoryResp();
  final int? code = jsonConvert.convert<int>(json['code']);
  if (code != null) {
    queryCategoryRespEntity.code = code;
  }
  final dynamic? msg = jsonConvert.convert<dynamic>(json['msg']);
  if (msg != null) {
    queryCategoryRespEntity.msg = msg;
  }
  final List<QueryCategoryRespData>? data = jsonConvert.convertListNotNull<QueryCategoryRespData>(json['data']);
  if (data != null) {
    queryCategoryRespEntity.data = data;
  }
  return queryCategoryRespEntity;
}

Map<String, dynamic> $QueryCategoryRespEntityToJson(QueryCategoryResp entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['code'] = entity.code;
  data['msg'] = entity.msg;
  data['data'] = entity.data.map((v) => v.toJson()).toList();
  return data;
}

QueryCategoryRespData $QueryCategoryRespDataFromJson(Map<String, dynamic> json) {
  final QueryCategoryRespData queryCategoryRespData = QueryCategoryRespData();
  final String? id = jsonConvert.convert<String>(json['id']);
  if (id != null) {
    queryCategoryRespData.id = id;
  }
  final String? name = jsonConvert.convert<String>(json['name']);
  if (name != null) {
    queryCategoryRespData.name = name;
  }
  return queryCategoryRespData;
}

Map<String, dynamic> $QueryCategoryRespDataToJson(QueryCategoryRespData entity) {
  final Map<String, dynamic> data = <String, dynamic>{};
  data['id'] = entity.id;
  data['name'] = entity.name;
  return data;
}
