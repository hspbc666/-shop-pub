import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/network/bean/query_category_resp_entity.dart';
import 'package:shop_flutter/network/bean/query_goods_by_category_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class CategoryPage extends StatefulWidget {
  const CategoryPage({Key? key}) : super(key: key);

  @override
  createState() => _CategoryState();
}

class _CategoryState extends State<CategoryPage> {
  List<QueryCategoryRespData> _categoryList = [];
  List<QueryGoodsByCategoryRespData> _goodsList = [];

  @override
  void initState() {
    super.initState();
    _queryCategory();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("X商城"),
        backgroundColor: LblColors.mainColor,
      ),
      body: Center(
        child: getBody(),
      ),
    );
  }

  getItem(QueryCategoryRespData queryCategoryRespData) {
    var row = Container(
      margin: const EdgeInsets.all(4.0),
      child: InkWell(
        onTap: () {
          onRowClick(queryCategoryRespData);
        },
        child: buildRow(queryCategoryRespData),
      ),
    );
    return Card(
      child: row,
    );
  }

  Row buildRow(QueryCategoryRespData queryCategoryRespData) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Container(
          margin: const EdgeInsets.only(left: 8.0),
          height: 40.0,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: <Widget>[
                  // Image(
                  //   image: NetworkImage(queryCategoryRespData.),
                  //   width: 50,
                  //   height: 50,
                  // ),
                  // Expanded(
                  //     child: Container(
                  //       margin: const EdgeInsets.only(left: 10),
                  //       child: buildGoodsInfoCol(cartItem),
                  //     ))
                ],
              ),
            ],
          ),
        ))
      ],
    );
  }

  getBody() {
    if (_categoryList.isNotEmpty) {
      return ListView.builder(
          itemCount: _categoryList.length,
          itemBuilder: (BuildContext context, int position) {
            return getItem(_categoryList[position]);
          });
    }
  }

  onRowClick(QueryCategoryRespData queryCategoryRespData) {
    // Navigator.push(context, MaterialPageRoute(builder: (context) => ViewNotePage(noteId: note['id'])));
  }

  _queryCategory() async {
    String url = "shop/category";
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryCategoryRespEntity.fromJson(resp);
      setState(() {
        _categoryList = result.data;
      });
    });
  }

  _queryGoodsByCategory() async {
    String url = "shop/goods/category/1";
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryGoodsByCategoryRespEntity.fromJson(resp);
      setState(() {
        _goodsList = result.data;
      });
    });
  }
}
