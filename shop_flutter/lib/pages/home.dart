import 'package:flutter/material.dart';
import 'package:flutter_swiper_null_safety/flutter_swiper_null_safety.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
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
      body: _buildBody(),
    );
  }

  _buildBody() {
    if (_goodsList.isEmpty) {
      return emptyContainer();
    } else {
      return Column(
        children: [_buildBanner(), buildItem(_goodsList[3]), buildItem(_goodsList[4])],
      );
    }
  }

  _buildBanner() {
    return SizedBox(
        height: 200,
        child: Swiper(
          itemCount: 3,
          itemBuilder: (BuildContext context, int index) {
            return Image.network(
              _goodsList[index].longPic,
              fit: BoxFit.cover,
            );
          },
          pagination: const SwiperPagination(),
          onTap: (index) {
            onItemClick(_goodsList[index]);
          },
        ));
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
