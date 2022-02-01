import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/search_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class SearchPage extends StatefulWidget {
  const SearchPage({Key? key}) : super(key: key);

  @override
  createState() => _SearchState();
}

class _SearchState extends State<SearchPage> {
  late BuildContext mContext;
  List<SearchRespData> _dataList = [];

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  @override
  Widget build(BuildContext context) {
    mContext = context;
    return Scaffold(
      body: Container(margin: const EdgeInsets.fromLTRB(20, 30, 20, 20), child: buildSearchResult()),
    );
  }

  buildSearchResult() {
    if (_dataList.isNotEmpty) {
      return Column(
        children: [
          ListTile(
            title: Text("ddddd"),
          ),
          ListView.builder(
              itemCount: _dataList.length,
              itemBuilder: (BuildContext context, int position) {
                return Container(
                  margin: const EdgeInsets.fromLTRB(10, 10, 10, 0),
                  child: Text(_dataList[position].name),
                );
              })
        ],
      );
    } else {
      return emptyContainer();
    }
  }

  _queryData() async {
    String url = "shop/goods/search/" + "手";
    HttpManager.getInstance().get(url).then((resp) {
      var result = SearchRespEntity.fromJson(resp);
      setState(() {
        _dataList = result.data;
      });
    });
  }
}
