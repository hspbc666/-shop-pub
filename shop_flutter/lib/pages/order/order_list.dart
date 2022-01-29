import 'package:flutter/material.dart';

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
              padding: const EdgeInsets.all(16.0),
              child: OrderListWidget(orderTab: orderTab),
            );
          }).toList(),
        ),
      ),
    );
  }
}

class OrderListWidget extends StatelessWidget {
  const OrderListWidget({Key? key, required this.orderTab}) : super(key: key);

  final OrderTab orderTab;
  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.white,
      child: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(orderTab.name),
          ],
        ),
      ),
    );
  }
}

class OrderTab {
  int code = 0;
  String name = "";

  OrderTab(this.code, this.name);
}
