import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/query_goods_detail_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/addr/addr_list.dart';
import 'package:shop_flutter/pages/login/login_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class GoodsPage extends StatefulWidget {
  String goodsId;

  GoodsPage(this.goodsId, {Key? key}) : super(key: key);

  @override
  createState() => _GoodsState();
}

class _GoodsState extends State<GoodsPage> {
  QueryGoodsDetailRespData? queryGoodsDetailRespData;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("商品详情"),
        backgroundColor: LblColors.mainColor,
      ),
      body: Container(
          margin: const EdgeInsets.fromLTRB(5, 20, 10, 0),
          child: Column(
            children: [
              Expanded(
                  child: Column(children: [
                Container(
                  decoration: const BoxDecoration(
                    //设置背景色
                    color: Colors.white,
                  ),
                  child: buildGoodsInfoBlock(),
                  padding: const EdgeInsets.all(10),
                ),
              ])),
              buildBottomRow()
            ],
          )),
    );
  }

  buildGoodsInfoBlock() {
    if (queryGoodsDetailRespData == null) {
      return emptyContainer();
    } else {
      return Column(
        children: [
          Image(
            image: NetworkImage(queryGoodsDetailRespData!.squarePic),
            width: 100,
            height: 100,
          ),
          myVerticalSpacer(10),
        ],
      );
    }
  }

  buildUserInfo() {
    return Row(
      children: <Widget>[
        const Image(
          image: AssetImage('assets/images/user.png'),
          width: 50,
          height: 50,
        ),
        Container(
          padding: EdgeInsets.only(left: 20),
          child: Column(
            children: const [
              Text("尊贵会员"),
              Text("级别：白银", style: TextStyle(fontSize: 12.0, color: Color(0xFF9E9EA2))),
            ],
          ),
        ),
        Spacer(),
        Row(
          children: const [
            Text('查看个人资料'),
            Image(
              image: AssetImage('assets/images/right_arrow.png'),
              width: 20,
              height: 20,
            )
          ],
        )
      ],
    );
  }

  buildRowWithArrow(String text, GestureTapCallback? onTap) {
    return InkWell(
      child: Row(
        children: <Widget>[
          Text(text),
          const Spacer(),
          const Image(
            image: AssetImage('assets/images/right_arrow.png'),
            width: 20,
            height: 20,
          )
        ],
      ),
      onTap: onTap,
    );
  }

  Container buildBottomRow() {
    return Container(
      margin: EdgeInsets.only(bottom: 20),
      width: double.infinity,
      child: SizedBox(
        child: ElevatedButton(
          child: const Text('退出登录', style: const TextStyle(color: Color(0xFFEF3965))),
          onPressed: () {
            LoginManager.logout();
            Navigator.pop(context);
          },
          style: ButtonStyle(backgroundColor: MaterialStateProperty.all(Colors.white)),
        ),
      ),
    );
  }

  void gotoAddrPage() {
    Navigator.push(context, MaterialPageRoute(builder: (context) => AddrListPage()));
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/goods/query/" + widget.goodsId;
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryGoodsDetailRespEntity.fromJson(resp);
      setState(() {
        queryGoodsDetailRespData = result.data;
      });
    });
  }
}
