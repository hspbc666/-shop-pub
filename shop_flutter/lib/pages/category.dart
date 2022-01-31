import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
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
    _queryGoodsByCategory();
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

  getBody() {
    if (_goodsList.isNotEmpty) {
      return ListView.builder(
          itemCount: _goodsList.length,
          itemBuilder: (BuildContext context, int position) {
            return Container(
              margin: const EdgeInsets.fromLTRB(10, 10, 10, 0),
              child: getItem(_goodsList[position]),
            );
          });
    }
  }

  getItem(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
    return Card(
      child: Container(
        margin: const EdgeInsets.all(10.0),
        child: InkWell(
          onTap: () {
            onRowClick(queryGoodsByCategoryRespData);
          },
          child: buildRow(queryGoodsByCategoryRespData),
        ),
      ),
    );
  }

  Row buildRow(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
    return Row(
      children: [
        Image(
          image: NetworkImage(queryGoodsByCategoryRespData.squarePic),
          width: 100,
          height: 100,
        ),
        myVerticalSpacer(10),
        Expanded(child: Text(queryGoodsByCategoryRespData.name, maxLines: 2, overflow: TextOverflow.ellipsis)),
        Column(
          children: [
            Text("￥" + (queryGoodsByCategoryRespData.price / 100).toString()),
          ],
        )
      ],
    );
  }

  onRowClick(QueryGoodsByCategoryRespData queryGoodsByCategoryRespData) {
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
