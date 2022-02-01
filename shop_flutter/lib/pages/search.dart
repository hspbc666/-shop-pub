import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/search_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/goods.dart';

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
  var keywordController = TextEditingController();

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  @override
  Widget build(BuildContext context) {
    mContext = context;
    return Scaffold(
      appBar: AppBar(
        title: TextField(
          autofocus: true,
          style: const TextStyle(color: Color(0XFF222222)),
          decoration: const InputDecoration(
              filled: true, fillColor: Colors.white, hintStyle: TextStyle(color: Color(0XFF97999E)), hintText: "点击搜索"),
          controller: keywordController,
        ),
        actions: [
          IconButton(
              icon: const Icon(Icons.search),
              onPressed: () {
                search();
              })
        ],
      ),
      body: Container(margin: const EdgeInsets.fromLTRB(10, 10, 10, 10), child: buildSearchResult()),
    );
  }

  buildSearchResult() {
    if (_dataList.isNotEmpty) {
      return ListView.builder(
          shrinkWrap: true,
          itemCount: _dataList.length,
          itemBuilder: (BuildContext context, int position) {
            return getItem(_dataList[position]);
          });
    } else {
      return emptyContainer();
    }
  }

  getItem(SearchRespData searchRespData) {
    return Card(
      child: Container(
        margin: const EdgeInsets.all(10.0),
        child: InkWell(
          onTap: () {
            onRowClick(searchRespData);
          },
          child: buildRow(searchRespData),
        ),
      ),
    );
  }

  Row buildRow(SearchRespData searchRespData) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.center,
      children: <Widget>[
        Image(
          image: NetworkImage(searchRespData.squarePic),
          width: 100,
          height: 100,
        ),
        Expanded(
            child: Container(
          margin: const EdgeInsets.only(left: 10),
          child: buildGoodsInfoCol(searchRespData),
        ))
      ],
    );
  }

  Column buildGoodsInfoCol(SearchRespData searchRespData) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(searchRespData.name,
            style: const TextStyle(
              fontSize: 18.0,
            ),
            maxLines: 2,
            overflow: TextOverflow.ellipsis),
        Row(
          children: [
            Text(
              "￥" + (searchRespData.price / 100).toString(),
              style: const TextStyle(fontSize: 16.0, color: Color(0xFFEF3965)),
              maxLines: 1,
            ),
          ],
        )
      ],
    );
  }

  onRowClick(SearchRespData searchRespData) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => GoodsPage(searchRespData.id)));
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

  void search() {
    String url = "shop/goods/search/" + keywordController.value.text;
    HttpManager.getInstance().get(url).then((resp) {
      var result = SearchRespEntity.fromJson(resp);
      setState(() {
        _dataList = result.data;
      });
    });
  }
}
