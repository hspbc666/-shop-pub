import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/constants.dart';
import 'package:shop_flutter/network/bean/query_category_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/login/login_manager.dart';

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
  List<QueryCategoryRespData> _dataList = [];

  @override
  void initState() {
    super.initState();
    _queryData();
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
              Text(
                queryCategoryRespData.name,
                style: const TextStyle(
                  fontSize: 18.0,
                ),
                maxLines: 1,
              ),
            ],
          ),
        ))
      ],
    );
  }

  getBody() {
    if (_dataList.isNotEmpty) {
      return ListView.builder(
          itemCount: _dataList.length,
          itemBuilder: (BuildContext context, int position) {
            return getItem(_dataList[position]);
          });
    }
  }

  onRowClick(note) {
    // Navigator.push(context, MaterialPageRoute(builder: (context) => ViewNotePage(noteId: note['id'])));
  }

  _queryData() async {
    LoginManager.isLoggedIn().then((value) {
      if (value) {
        String url = "shop/goods/category/1";
        HttpManager.getInstance().get(url).then((resp) {
          var result = QueryCategroyResp.fromJson(resp);
          setState(() {
            _dataList = result.data;
          });
        });
      }
    });
  }
}
