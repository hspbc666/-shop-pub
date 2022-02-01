import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/create_order_req.dart';
import 'package:shop_flutter/network/bean/query_default_addr_resp_entity.dart';
import 'package:shop_flutter/network/bean/query_goods_detail_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/addr/addr_add.dart';
import 'package:shop_flutter/pages/addr/addr_select.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class OrderConfirmPage extends StatefulWidget {
  final String goodsId;

  const OrderConfirmPage(this.goodsId, {Key? key}) : super(key: key);

  @override
  createState() => _OrderConfirmState();
}

class _OrderConfirmState extends State<OrderConfirmPage> {
  QueryGoodsDetailRespData? queryGoodsDetailRespData;
  QueryDefaultAddrRespData? queryDefaultAddrRespData;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("确认订单"),
        backgroundColor: LblColors.mainColor,
      ),
      body: buildBody(),
    );
  }

  buildBody() {
    if (queryGoodsDetailRespData == null) {
      return emptyContainer();
    } else {
      return Container(
          margin: const EdgeInsets.fromLTRB(5, 10, 10, 0),
          child: Column(
            children: [
              Expanded(
                  child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
                buildAddrInfoBlockWithTap(),
                buildGoodsInfoBlock(),
                buildDeliveryInfoBlock(),
                buildFeeInfoBlock(),
              ])),
              lblDivider(),
              buildBottomRow()
            ],
          ));
    }
  }

  buildAddrInfoBlockWithTap() {
    return InkWell(
      child: buildAddrInfoBlock(),
      onTap: () {
        gotoAddrPage();
      },
    );
  }

  buildAddrInfoBlock() {
    if (queryDefaultAddrRespData == null) {
      return Card(
          child: Container(
        width: double.infinity,
        padding: const EdgeInsets.all(20),
        child: const Text("+ 添加收货地址",
            style: TextStyle(
              fontSize: 16.0,
            )),
      ));
    } else {
      return Card(
        child: Container(
          padding: const EdgeInsets.all(20),
          child: Row(
            children: [
              const Image(
                image: AssetImage('assets/images/location.png'),
                width: 20,
                height: 20,
              ),
              lblRowSpacer(10),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(queryDefaultAddrRespData!.name,
                      style: const TextStyle(
                        color: Color(0xFF333333),
                      )),
                  Text(queryDefaultAddrRespData!.address, style: TextStyle(color: const Color(0xFF717171))),
                ],
              ),
              const Spacer(),
              const Image(
                image: AssetImage('assets/images/right_arrow.png'),
                width: 20,
                height: 20,
              ),
            ],
          ),
        ),
      );
    }
  }

  buildGoodsInfoBlock() {
    return Card(
      child: Row(
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Image(
            image: NetworkImage(queryGoodsDetailRespData!.squarePic),
            width: 100,
            height: 100,
          ),
          const Spacer(),
          const Text("共1种", style: TextStyle(color: Color(0xFF5A5E65))),
          lblRowSpacer(10)
        ],
      ),
    );
  }

  buildDeliveryInfoBlock() {
    return Card(
      child: Container(
        padding: const EdgeInsets.all(20),
        child: Row(
          children: [
            const Text("配送", style: TextStyle(color: Color(0xFF595D65))),
            lblRowSpacer(10),
            Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: const [
                Text("顺丰速运",
                    style: TextStyle(
                      color: Color(0xFF333333),
                      fontWeight: FontWeight.bold,
                    )),
                Text("1个包裹，预计明天送达", style: TextStyle(color: Color(0xFF717171))),
              ],
            ),
            const Spacer(),
            const Image(
              image: AssetImage('assets/images/right_arrow.png'),
              width: 20,
              height: 20,
            ),
          ],
        ),
      ),
    );
  }

  buildFeeInfoBlock() {
    return Card(
      child: Container(
        padding: const EdgeInsets.all(20),
        child: Column(
          children: [
            Row(
              children: [
                const Text("商品金额", style: TextStyle(color: Color(0xFF595D65))),
                const Spacer(),
                Text("￥" + (queryGoodsDetailRespData!.price / 100).toString(),
                    style: const TextStyle(color: Color(0xFF222222))),
              ],
            ),
            Row(
              children: const [
                Text("总运费", style: TextStyle(color: Color(0xFF595D65))),
                Spacer(),
                Text("满XX元 免邮", style: TextStyle(color: Color(0xFF222222))),
              ],
            ),
            Row(
              children: const [
                Text("优惠券", style: TextStyle(color: Color(0xFF595D65))),
                Spacer(),
                Text("无可用券", style: TextStyle(color: Color(0xFF222222))),
              ],
            ),
          ],
        ),
      ),
    );
  }

  Container buildBottomRow() {
    return Container(
        margin: const EdgeInsets.only(bottom: 10),
        child: Row(
          children: [
            const Spacer(),
            InkWell(
              onTap: () {},
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
                child: const Text('提交订单'),
                onPressed: () {
                  createOrder();
                },
              ),
            ),
            const Spacer(),
          ],
        ));
  }

  @override
  void initState() {
    super.initState();
    refreshPage();
  }

  refreshPage() {
    _queryGoods();
    _queryDefaultAddr();
  }

  _queryGoods() async {
    String url = "shop/goods/query/" + widget.goodsId;
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryGoodsDetailRespEntity.fromJson(resp);
      setState(() {
        queryGoodsDetailRespData = result.data;
      });
    });
  }

  _queryDefaultAddr() async {
    String url = "shop/addr/query_default";
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryDefaultAddrRespEntity.fromJson(resp);
      setState(() {
        queryDefaultAddrRespData = result.data;
      });
    });
  }

  void createOrder() {
    String url = "shop/order/create" + widget.goodsId;
    CreateOrderReq createOrderReq = CreateOrderReq(goodsId: widget.goodsId, userAddrId: "1111");
    HttpManager.getInstance().post(url, data: createOrderReq.toJson()).then((resp) {});
  }

  void gotoAddrPage() {
    if (queryDefaultAddrRespData == null) {
      Navigator.push(context, MaterialPageRoute(builder: (context) => const AddAddrPage()))
          .then((value) => {refreshPage()});
    } else {
      Navigator.push(context,
              MaterialPageRoute(builder: (context) => SelectAddrListPage(userAddrId: queryDefaultAddrRespData!.id)))
          .then((value) => {refreshPage()});
    }
  }
}
