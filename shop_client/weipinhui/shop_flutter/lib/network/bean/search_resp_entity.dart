import 'dart:convert';

import 'package:shop_flutter/generated/json/base/json_field.dart';
import 'package:shop_flutter/generated/json/search_resp_entity.g.dart';

@JsonSerializable()
class SearchRespEntity {
  late int code;
  late dynamic msg;
  late List<SearchRespData> data;

  SearchRespEntity();

  factory SearchRespEntity.fromJson(Map<String, dynamic> json) => $SearchRespEntityFromJson(json);

  Map<String, dynamic> toJson() => $SearchRespEntityToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}

@JsonSerializable()
class SearchRespData {
  late String id;
  late String name;
  late int price;
  late String squarePic;

  SearchRespData();

  factory SearchRespData.fromJson(Map<String, dynamic> json) => $SearchRespDataFromJson(json);

  Map<String, dynamic> toJson() => $SearchRespDataToJson(this);

  @override
  String toString() {
    return jsonEncode(this);
  }
}
