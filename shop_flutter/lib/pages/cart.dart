import 'dart:developer';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/lblbc_ui_kit.dart';
import 'package:shop_flutter/network/bean/query_cart_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/login/login_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class CartPage extends StatefulWidget {
  const CartPage({Key? key}) : super(key: key);

  @override
  createState() => _CartState();
}

class _CartState extends State<CartPage> {
  List<QueryCartRespData> _dataList = [];
  List<String> selectedCartIdList = [];
  int selectedSum = 0;

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("购物车"),
        backgroundColor: LblColors.mainColor,
      ),
      body: Container(
          margin: const EdgeInsets.fromLTRB(5, 20, 10, 0),
          child: Column(
            children: [Expanded(child: buildCartInfoList()), defaultDivider(), buildBottomRow()],
          )),
    );
  }

  Container buildBottomRow() {
    return Container(
      child: Row(
        children: [
          const Spacer(flex: 1),
          Text("已选(" + selectedCartIdList.length.toString() + ")"),
          const Spacer(flex: 1),
          const Text("总计"),
          Text("￥" + selectedSum.toString(), style: const TextStyle(fontSize: 16.0, color: Color(0xFFEF3965))),
          const Spacer(flex: 3),
          SizedBox(
            width: 100,
            child: ElevatedButton(
              child: const Text('结算'),
              onPressed: () {},
              style: ButtonStyle(backgroundColor: MaterialStateProperty.all(buildBtnColor())),
            ),
          )
        ],
      ),
    );
  }

  Color buildBtnColor() {
    if (selectedCartIdList.isNotEmpty) {
      return const Color(0xFFEF3965);
    } else {
      return const Color(0xFFCBCCD2);
    }
  }

  getItem(QueryCartRespData cartItem) {
    var row = Container(
      margin: EdgeInsets.all(4.0),
      child: InkWell(
        onTap: () {
          onRowClick(cartItem);
        },
        child: buildRow(cartItem),
      ),
    );
    return Card(
      child: row,
    );
  }

  Row buildRow(QueryCartRespData cartItem) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Container(
          padding: const EdgeInsets.only(top: 5, bottom: 10),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              buildCheckbox(cartItem),
              Image(
                image: NetworkImage(cartItem.squarePic),
                width: 50,
                height: 50,
              ),
              Expanded(
                  child: Container(
                margin: const EdgeInsets.only(left: 10),
                child: buildGoodsInfoCol(cartItem),
              ))
            ],
          ),
        ))
      ],
    );
  }

  Column buildGoodsInfoCol(QueryCartRespData cartItem) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Text(
          cartItem.name,
          style: const TextStyle(
            fontSize: 18.0,
          ),
          maxLines: 2,
        ),
        Row(
          children: [
            Text(
              "￥" + (cartItem.price / 100).toString(),
              style: const TextStyle(fontSize: 16.0, color: Color(0xFFEF3965)),
              maxLines: 1,
            ),
            const Spacer(),
            Container(
              height: 30,
              decoration: BoxDecoration(
                borderRadius: const BorderRadius.all(Radius.circular(50.0)),
                border: Border.all(width: 1, color: const Color(0XFFEBEBEB)),
              ),
              child: Row(
                crossAxisAlignment: CrossAxisAlignment.center,
                children: [
                  buildDecreaseQuantityView(),
                  const VerticalDivider(
                    width: 1,
                    thickness: 1,
                    color: Color(0XFFEBEBEB),
                  ),
                  Container(
                      padding: const EdgeInsets.only(left: 10, right: 10), child: Text(cartItem.quantity.toString())),
                  const VerticalDivider(
                    width: 1,
                    thickness: 1,
                    color: Color(0XFFEBEBEB),
                  ),
                  buildIncreaseQuantityView(),
                ],
              ),
            )
          ],
        )
      ],
    );
  }

  InkWell buildDecreaseQuantityView() {
    return InkWell(
      child: Container(
        padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
        child: const Text("-",
            style: TextStyle(
              fontSize: 18.0,
            )),
      ),
      onTap: () {
        decreaseQuantity();
      },
    );
  }

  InkWell buildIncreaseQuantityView() {
    return InkWell(
      child: Container(
        padding: const EdgeInsets.fromLTRB(10, 0, 10, 0),
        child: const Text("+",
            style: TextStyle(
              fontSize: 18.0,
            )),
      ),
      onTap: () {
        increaseQuantity();
      },
    );
  }

  Checkbox buildCheckbox(QueryCartRespData cartItem) {
    return Checkbox(
      value: selectedCartIdList.contains(cartItem.id),
      onChanged: (isChecked) {
        setState(() {
          if (isChecked == true) {
            selectedCartIdList.add(cartItem.id);
          } else {
            selectedCartIdList.remove(cartItem.id);
          }
          calcSum();
        });
      },
    );
  }

  //TODO: 计算总计金额
  void calcSum() {
    int sum = 0;
    for (int i = 0; i < selectedCartIdList.length; i++) {
      // sum += _dataList.
    }
    selectedSum = sum;
  }

  buildCartInfoList() {
    if (_dataList.isNotEmpty) {
      return ListView.builder(
        itemCount: _dataList.length,
        itemBuilder: (BuildContext context, int position) {
          return getItem(_dataList[position]);
        },
      );
    } else {
      return emptyContainer();
    }
  }

  onRowClick(QueryCartRespData cartItem) {
    // Navigator.push(context, MaterialPageRoute(builder: (context) => ViewNotePage(noteId: note['id'])));
  }

  void decreaseQuantity() {
    log("ddddd");
  }

  void increaseQuantity() {
    log("ddddd3333333333");
  }

  _queryData() async {
    LoginManager.isLoggedIn().then((value) {
      if (value) {
        String url = "shop/cart/list";
        HttpManager.getInstance().get(url).then((resp) {
          var result = QueryCartRespEntity.fromJson(resp);
          setState(() {
            _dataList = result.data;
          });
        });
      }
    });
  }
}
