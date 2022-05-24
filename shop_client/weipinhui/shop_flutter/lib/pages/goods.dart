import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:fluttertoast/fluttertoast.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/query_goods_detail_resp_entity.dart';
import 'package:shop_flutter/network/bean/add_to_cart_req_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/login/login.dart';
import 'package:shop_flutter/pages/login/login_manager.dart';
import 'package:shop_flutter/pages/order/order_confirm.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class GoodsPage extends StatefulWidget {
  final String goodsId;

  const GoodsPage(this.goodsId, {Key? key}) : super(key: key);

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
                buildGoodsInfoBlock(),
              ])),
              lblDivider(),
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
            width: 200,
            height: 200,
          ),
          lblColumnSpacer(10),
          Card(
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Padding(
                  padding: const EdgeInsets.only(left: 10),
                  child: Text("￥" + queryGoodsDetailRespData!.price.toString(),
                      style: const TextStyle(fontSize: 16.0, color: Color(0xFFEF3965))),
                ),
                Row(
                  children: [
                    lblRowSpacer(10),
                    OutlinedButton(
                      child: const Text('券 ￥5', style: TextStyle(color: Color(0xFFEF3965))),
                      onPressed: () {},
                      style: OutlinedButton.styleFrom(
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(5.0),
                        ),
                        side: const BorderSide(width: 1, color: Color(0xFFEF3965)),
                      ),
                    ),
                    const Spacer(),
                    ElevatedButton(
                      child: const Text('领券>'),
                      onPressed: () {},
                      style: ElevatedButton.styleFrom(
                        shape: const RoundedRectangleBorder(
                          borderRadius:
                              BorderRadius.only(topLeft: Radius.circular(18), bottomLeft: Radius.circular(18)),
                        ),
                      ),
                    )
                  ],
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 10),
                  child: ElevatedButton(
                    child: const Text('X商自营'),
                    onPressed: () {},
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.only(left: 10),
                  child:
                      Text(queryGoodsDetailRespData!.name, style: const TextStyle(fontSize: 16.0, color: Colors.black)),
                ),
                Row(
                  children: [
                    lblRowSpacer(10),
                    const Image(
                      image: AssetImage('assets/images/secure.png'),
                      width: 20,
                      height: 20,
                    ),
                    const Text("100%正品", style: TextStyle(color: Colors.black)),
                    lblRowSpacer(10),
                    const Image(
                      image: AssetImage('assets/images/good.png'),
                      width: 20,
                      height: 20,
                    ),
                    const Text("满88免邮", style: TextStyle(color: Color(0XFF595d63))),
                    lblRowSpacer(10),
                    const Image(
                      image: AssetImage('assets/images/good.png'),
                      width: 20,
                      height: 20,
                    ),
                    const Text("7天可退", style: TextStyle(color: Color(0XFF595d63))),
                    lblRowSpacer(10),
                    const Image(
                      image: AssetImage('assets/images/good.png'),
                      width: 20,
                      height: 20,
                    ),
                    const Text("上门退货", style: TextStyle(color: Color(0XFF595d63))),
                  ],
                ),
                lblColumnSpacer(10),
              ],
            ),
          )
        ],
      );
    }
  }

  Container buildBottomRow() {
    return Container(
        margin: const EdgeInsets.only(bottom: 10),
        child: Row(
          children: [
            const Spacer(),
            InkWell(
              onTap: () {
                addToCart();
              },
              child: Row(children: const [
                Text("购物车", style: TextStyle(fontSize: 16.0, color: Color(0xFF737373))),
                Image(
                  image: AssetImage('assets/images/cart.png'),
                  width: 30,
                  height: 30,
                )
              ]),
            ),
            const Spacer(),
            SizedBox(
              width: 120,
              child: ElevatedButton(
                child: const Text('购买'),
                onPressed: () {
                  gotoConfirmOrderPage();
                },
              ),
            ),
            const Spacer(),
          ],
        ));
  }

  void gotoConfirmOrderPage() {
    if (queryGoodsDetailRespData != null) {
      Navigator.push(context, MaterialPageRoute(builder: (context) => OrderConfirmPage(queryGoodsDetailRespData!.id)));
    }
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/goods/" + widget.goodsId;
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryGoodsDetailRespEntity.fromJson(resp);
      setState(() {
        queryGoodsDetailRespData = result.data;
      });
    });
  }

  void addToCart() {
    LoginManager.isLoggedIn().then((value) {
      if (value) {
        String url = "shop/cart";
        AddToCartReqEntity request = AddToCartReqEntity();
        request.goodsId = widget.goodsId;
        HttpManager.getInstance().post(url, data: request.toJson()).then((resp) {
          Fluttertoast.showToast(
            msg: "已加入购物车",
          );
        });

      } else {
        Navigator.push(context, MaterialPageRoute(builder: (context) => const LoginPage()));
      }
    });
  }
}
