import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/network/bean/query_order_detail_resp.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/ui_kit.dart';

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
  QueryOrderDetailData? _queryOrderDetailData;

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: buildOrderBlock(),
    );
  }

  Column buildOrderBlock() {
    return Column(
      children: [
        Row(
          children: const [Text("X商自营", style: TextStyle(fontSize: 18.0, color: Colors.black)), Spacer(), Text("状态")],
        ),
        Column(
          children: buildOrderDetailList(),
        ),
        mySpacer(10),
        const Divider(
          height: 10,
          thickness: 1,
        ),
        Row(
          children: [
            Spacer(),
            OutlinedButton(
              child: const Text('发票详情', style: TextStyle(color: Color(0xFF575E64))),
              onPressed: () {},
            ),
            myVerticalSpacer(10),
            OutlinedButton(
              child: const Text('申请售后', style: TextStyle(color: Color(0xFF575E64))),
              onPressed: () {},
            )
          ],
        )
      ],
    );
  }

  List<Column> buildOrderDetailList() {
    if (_queryOrderDetailData == null) {
      return emptyContainer();
    } else {
      return _queryOrderDetailData!.list.map((QueryOrderDetailDataItem queryOrderDetailDataItem) {
        return Column(
          children: [
            Row(
              children: [
                Image(
                  image: NetworkImage(queryOrderDetailDataItem.squarePic),
                  width: 100,
                  height: 100,
                ),
                myVerticalSpacer(10),
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

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/order/query/" + widget.orderId;
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryOrderDetailResp.fromJson(resp);
      setState(() {
        _queryOrderDetailData = result.data;
      });
    });
  }
}
