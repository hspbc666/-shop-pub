import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/query_order_list_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/order/order_detail.dart';

class OrderListPage extends StatefulWidget {
  final int orderStatus;

  const OrderListPage(this.orderStatus, {Key? key}) : super(key: key);

  @override
  createState() => _OrderListState();
}

class _OrderListState extends State<OrderListPage> {
  final List<OrderTab> _tabList = <OrderTab>[
    OrderTab(0, "全部"),
    OrderTab(1, "待付款"),
    OrderTab(2, "待发货"),
    OrderTab(3, "待收货"),
    OrderTab(4, "待评价"),
    OrderTab(5, "退换/售后"),
  ];
  int currentTab = 0;

  @override
  void initState() {
    super.initState();
    currentTab = widget.orderStatus;
  }

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
      initialIndex: currentTab,
      length: _tabList.length,
      child: Scaffold(
        appBar: AppBar(
          title: const Text('我的订单'),
          bottom: TabBar(
            isScrollable: true,
            tabs: _tabList.map((OrderTab orderTab) {
              return Tab(
                text: orderTab.name,
              );
            }).toList(),
          ),
        ),
        body: TabBarView(
          children: _tabList.map((OrderTab orderTab) {
            return Padding(
              padding: const EdgeInsets.all(10.0),
              child: OrderListWidget(orderTab.status),
            );
          }).toList(),
        ),
      ),
    );
  }
}

class OrderListWidget extends StatefulWidget {
  final int status;

  const OrderListWidget(this.status, {Key? key}) : super(key: key);

  @override
  _OrderListWidgetState createState() {
    return _OrderListWidgetState();
  }
}

class _OrderListWidgetState extends State<OrderListWidget> {
  List<QueryOrderListRespData> _dataList = [];

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Column(
        mainAxisSize: MainAxisSize.min,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: _dataList.map((QueryOrderListRespData queryOrderListRespData) {
          return Card(
            color: Colors.white,
            child: Container(
              padding: const EdgeInsets.all(10),
              child: InkWell(
                child: buildOrderBlock(queryOrderListRespData),
                onTap: () {
                  gotoOrderDetailPage(queryOrderListRespData.orderId);
                },
              ),
            ),
          );
        }).toList(),
      ),
    );
  }

  Column buildOrderBlock(QueryOrderListRespData queryOrderListRespData) {
    return Column(
      children: [
        Row(
          children: [
            const Text("蓝不蓝自营", style: TextStyle(fontSize: 18.0, color: Colors.black)),
            const Spacer(),
            Text(OrderStatus.getStatusName(queryOrderListRespData.status))
          ],
        ),
        Column(
          children: queryOrderListRespData.list.map((QueryOrderListRespDataList queryOrderListRespDataItem) {
            return Column(
              children: [
                Row(
                  children: [
                    Image(
                      image: NetworkImage(queryOrderListRespDataItem.squarePic),
                      width: 100,
                      height: 100,
                    ),
                    lblRowSpacer(10),
                    Expanded(
                        child: Text(queryOrderListRespDataItem.name, maxLines: 2, overflow: TextOverflow.ellipsis)),
                    Column(
                      children: [
                        Text("￥" + (queryOrderListRespDataItem.price / 100).toString()),
                        Text("×" + queryOrderListRespDataItem.quantity.toString())
                      ],
                    )
                  ],
                ),
              ],
            );
          }).toList(),
        ),
        lblColumnSpacer(10),
        lblDivider(),
        Row(
          children: [
            Spacer(),
            OutlinedButton(
              child: const Text('发票详情', style: TextStyle(color: Color(0xFF575E64))),
              onPressed: () {},
            ),
            lblRowSpacer(10),
            OutlinedButton(
              child: const Text('申请售后', style: TextStyle(color: Color(0xFF575E64))),
              onPressed: () {},
            )
          ],
        )
      ],
    );
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/orders?orderStatus=" + widget.status.toString();
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryOrderListRespEntity.fromJson(resp);
      setState(() {
        _dataList = result.data;
      });
    });
  }

  void gotoOrderDetailPage(String orderId) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => OrderDetailPage(orderId)))
        .then((value) => {_queryData()});
  }
}

class OrderTab {
  int status = 0;
  String name = "";

  OrderTab(this.status, this.name);
}
