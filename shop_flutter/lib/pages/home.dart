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
  List<String> imageUrls = [
    "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2022/01/06/102/ias_a28a4efde3be0890bb22724e5dedaeb5_1135x545_85.jpg",
    "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/07/30/160/ias_86095df3cfe17ce6437098d7d0519d9f_1135x545_85.jpg",
    "https://h2.appsimg.com/a.appsimg.com/upload/brand/upcb/2021/11/01/161/ias_f2b8d6ecbc6ec61a89d20d19a6a98b8c_1135x545_85.jpg",
  ];

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
      body: Column(
        children: [_buildBanner()],
      ),
    );
  }

  _buildBanner() {
    if (_goodsList.isEmpty) {
      return emptyContainer();
    } else {
      return Container(
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
          ));
    }
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
