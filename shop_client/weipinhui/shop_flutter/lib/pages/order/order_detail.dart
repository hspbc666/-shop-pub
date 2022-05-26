import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/query_order_detail_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class OrderDetailPage extends StatefulWidget {
  String orderId;

  OrderDetailPage(this.orderId, {Key? key}) : super(key: key);

  @override
  createState() => _OrderDetailState();
}

class _OrderDetailState extends State<OrderDetailPage> {
  QueryOrderDetailRespData? _queryOrderDetailData;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("订单详情"),
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
                  child: Column(
                    children: [
                      Row(
                        children: [
                          Text("X商自营", style: TextStyle(fontSize: 18.0, color: Colors.black)),
                          Spacer(),
                          Text(OrderStatus.getStatusName(_queryOrderDetailData?.status))
                        ],
                      ),
                      Column(
                        children: buildOrderDetailList(),
                      ),
                    ],
                  ),
                  padding: const EdgeInsets.all(10),
                ),
              ])),
              lblDivider(),
              buildBottomRow()
            ],
          )),
    );
  }

  List<Widget> buildOrderDetailList() {
    if (_queryOrderDetailData == null) {
      return [emptyContainer()];
    } else {
      return _queryOrderDetailData!.list.map((QueryOrderDetailRespDataList queryOrderDetailDataItem) {
        return Column(
          children: [
            Row(
              children: [
                Image(
                  image: NetworkImage(queryOrderDetailDataItem.squarePic),
                  width: 100,
                  height: 100,
                ),
                lblRowSpacer(10),
                Expanded(child: Text(queryOrderDetailDataItem.name, maxLines: 2, overflow: TextOverflow.ellipsis)),
                Column(
                  children: [
                    Text("￥" + (queryOrderDetailDataItem.price / 100).toString()),
                    Text("×" + queryOrderDetailDataItem.quantity.toString())
                  ],
                )
              ],
            ),
          ],
        );
      }).toList();
    }
  }

  Container buildBottomRow() {
    return Container(
      margin: const EdgeInsets.only(bottom: 20),
      width: double.infinity,
      child: Row(
        children: [
          const Spacer(),
          OutlinedButton(
            child: const Text('删除订单', style: TextStyle(color: Color(0xFF575E64))),
            onPressed: () {
              _deleteOrder();
            },
          ),
          lblRowSpacer(10),
          OutlinedButton(
            child: const Text('申请售后', style: TextStyle(color: Color(0xFF575E64))),
            onPressed: () {},
          )
        ],
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/orders/" + widget.orderId;
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryOrderDetailRespEntity.fromJson(resp);
      setState(() {
        _queryOrderDetailData = result.data;
      });
    });
  }

  _deleteOrder() async {
    String url = "shop/orders/" + widget.orderId;
    HttpManager.getInstance().delete(url).then((resp) {
      Navigator.pop(context);
    });
  }
}
