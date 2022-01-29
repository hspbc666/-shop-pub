import 'package:flutter/material.dart';

class OrderListPage extends StatefulWidget {
  const OrderListPage({Key? key}) : super(key: key);

  @override
  createState() => _OrderListState();
}

class _OrderListState extends State<OrderListPage> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: DefaultTabController(
        length: tabNameList.length,
        child: Scaffold(
          appBar: AppBar(
            title: const Text('Tabbed AppBar'),
            bottom: TabBar(
              isScrollable: true,
              tabs: tabNameList.map((String tabName) {
                return Tab(
                  text: tabName,
                );
              }).toList(),
            ),
          ),
          body: TabBarView(
            children: tabNameList.map((String tabName) {
              return Padding(
                padding: const EdgeInsets.all(16.0),
                child: TabNameCard(tabName: tabName),
              );
            }).toList(),
          ),
        ),
      ),
    );
  }
}

const List<String> tabNameList = <String>["qqqq", "111111", "33333"];

class TabNameCard extends StatelessWidget {
  const TabNameCard({Key? key, required this.tabName}) : super(key: key);

  final String tabName;

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.white,
      child: Center(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: <Widget>[
            Text(tabName),
          ],
        ),
      ),
    );
  }
}
