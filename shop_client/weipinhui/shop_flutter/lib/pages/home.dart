import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/query_category_resp_entity.dart';
import 'package:shop_flutter/network/bean/query_goods_by_category_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/goods.dart';
import 'package:shop_flutter/pages/search.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》  http://lblbc.cn/blog
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class HomePage extends StatefulWidget {
  const HomePage({Key? key}) : super(key: key);

  @override
  createState() => _HomeState();
}

class _HomeState extends State<HomePage> {
  List<QueryCategoryRespData> _categoryList = [];

  @override
  void initState() {
    super.initState();
    _queryCategory();
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      length: _categoryList.length,
      child: Scaffold(
        appBar: AppBar(
            title: const Text('X商城'),
            bottom: TabBar(
              isScrollable: true,
              tabs: _categoryList.map((QueryCategoryRespData queryCategoryRespData) {
                return Tab(
                  text: queryCategoryRespData.name,
                );
              }).toList(),
            ),
            actions: [
              IconButton(
                  icon: const Icon(Icons.search),
                  onPressed: () {
                    gotoSearchPage();
                  })
            ]),
        body: TabBarView(
          children: _categoryList.map((QueryCategoryRespData queryCategoryRespData) {
            return Padding(
              padding: const EdgeInsets.all(10.0),
              child: OrderListWidget(queryCategoryRespData.id),
            );
          }).toList(),
        ),
      ),
    );
  }

  _queryCategory() async {
    String url = "shop/categories";
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryCategoryRespEntity.fromJson(resp);
      setState(() {
        _categoryList = result.data;
      });
    });
  }

  void gotoSearchPage() {
    Navigator.push(context, MaterialPageRoute(builder: (context) => const SearchPage()));
  }
}

class OrderListWidget extends StatefulWidget {
  final String categoryId;

  const OrderListWidget(this.categoryId, {Key? key}) : super(key: key);

  @override
  _OrderListWidgetState createState() {
    return _OrderListWidgetState();
  }
}

class _OrderListWidgetState extends State<OrderListWidget> {
  List<QueryGoodsByCategoryRespData> _goodsList = [];

  @override
  Widget build(BuildContext context) {
    if (_goodsList.isNotEmpty) {
      return ListView.builder(
          itemCount: _goodsList.length,
          itemBuilder: (BuildContext context, int position) {
            return Container(
              margin: const EdgeInsets.fromLTRB(10, 10, 10, 0),
              child: buildItem(_goodsList[position]),
            );
          });
    } else {
      return emptyContainer();
    }
  }

  buildItem(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
    return Card(
      child: Container(
        margin: const EdgeInsets.all(10.0),
        child: InkWell(
          onTap: () {
            onItemClick(queryGoodsByCategoryRespData);
          },
          child: buildRow(queryGoodsByCategoryRespData),
        ),
      ),
    );
  }

  Row buildRow(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Container(
          padding: const EdgeInsets.only(top: 5, bottom: 10),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Image(
                image: NetworkImage(queryGoodsByCategoryRespData.squarePic),
                width: 100,
                height: 100,
              ),
              Expanded(
                  child: Container(
                margin: const EdgeInsets.only(left: 10),
                child: buildGoodsInfoCol(queryGoodsByCategoryRespData),
              ))
            ],
          ),
        ))
      ],
    );
  }

  Column buildGoodsInfoCol(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(queryGoodsByCategoryRespData.name,
            style: const TextStyle(
              fontSize: 18.0,
            ),
            maxLines: 2,
            overflow: TextOverflow.ellipsis),
        Row(
          children: [
            Text(
              "￥" + (queryGoodsByCategoryRespData.price / 100).toString(),
              style: const TextStyle(fontSize: 16.0, color: Color(0xFFEF3965)),
              maxLines: 1,
            ),
          ],
        )
      ],
    );
  }

  onItemClick(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => GoodsPage(queryGoodsByCategoryRespData.id)));
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/goods?categoryId=" + widget.categoryId;
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryGoodsByCategoryRespEntity.fromJson(resp);
      setState(() {
        _goodsList = result.data;
      });
    });
  }
}
