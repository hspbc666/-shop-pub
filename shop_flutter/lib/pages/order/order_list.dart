import 'package:flutter/material.dart';
import 'package:shop_flutter/network/bean/query_order_list_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';

class OrderListPage extends StatefulWidget {
  const OrderListPage({Key? key}) : super(key: key);

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

  // List<ProjectListDataData> _listDatas = List();

  @override
  Widget build(BuildContext context) {
    return DefaultTabController(
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
    return Card(
      color: Colors.white,
      child: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: _dataList.map((QueryOrderListRespData queryOrderListRespData) {
            return Column(
              children: [
                Row(
                  children: const [
                    Text("X商自营", style: TextStyle(fontSize: 18.0, color: Colors.black)),
                    Spacer(),
                    Text("状态")
                  ],
                ),
                Column(
                  children: queryOrderListRespData.list.map((QueryOrderListRespDataItem queryOrderListRespDataItem) {
                    return Column(
                      children: [
                        Row(
                          children: [
                            Image(
                              image: NetworkImage(queryOrderListRespDataItem.squarePic),
                              width: 100,
                              height: 100,
                            ),
                            Expanded(
                                child:
                                    Text(queryOrderListRespDataItem.name, maxLines: 2, overflow: TextOverflow.ellipsis))
                          ],
                        ),
                      ],
                    );
                  }).toList(),
                )
              ],
            );
          }).toList(),
        ),
      ),
    );
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "shop/order/queryByStatus/" + widget.status.toString();
    HttpManager.getInstance().get(url).then((resp) {
      var result = QueryOrderListResp.fromJson(resp);
      setState(() {
        _dataList = result.data;
      });
    });
  }
}

class OrderTab {
  int status = 0;
  String name = "";

  OrderTab(this.status, this.name);
}
