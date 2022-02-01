import 'package:flutter/material.dart';
import 'package:shop_flutter/network/bean/query_goods_by_category_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/goods.dart';
import 'package:shop_flutter/pages/search.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  createState() => _HomeState();
}

class _HomeState extends State<HomePage> {
  List<QueryGoodsByCategoryRespData> _goodsList = [];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('X商城'), actions: [
        IconButton(
            icon: const Icon(Icons.search),
            onPressed: () {
              gotoSearchPage();
            })
      ]),
      body: Text("dddd"),
    );
  }

  onRowClick(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => GoodsPage(queryGoodsByCategoryRespData.id)));
  }

  void gotoSearchPage() {
    Navigator.push(context, MaterialPageRoute(builder: (context) => const SearchPage()));
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/goods/category/0";
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryGoodsByCategoryRespEntity.fromJson(resp);
      setState(() {
        _goodsList = result.data;
      });
    });
  }
}
