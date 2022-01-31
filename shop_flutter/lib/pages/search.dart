import 'package:flutter/material.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/pages/login/login.dart';
import 'package:shop_flutter/pages/login/login_manager.dart';
import 'package:shop_flutter/pages/order/order_list.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class SearchPage extends StatefulWidget {
  const SearchPage({Key? key}) : super(key: key);

  @override
  createState() => _SearchState();
}

class _SearchState extends State<SearchPage> {
  late BuildContext mContext;

  @override
  void initState() {
    super.initState();
    // initUserName();
  }

  @override
  Widget build(BuildContext context) {
    mContext = context;
    return Scaffold(
      body: Container(
          margin: const EdgeInsets.fromLTRB(20, 30, 20, 20),
          child: Column(
            children: [
              buildSettings(),
            ],
          )),
    );
  }

  Row buildOrderBtnRow() {
    return Row(
      children: [
        InkWell(
          child: buildToOrderBtns('assets/images/to_pay.png', "待付款"),
          onTap: () {
            gotoOrderPage(OrderStatus.orderStatusToPay);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_deliver.png', "待发货"),
          onTap: () {
            gotoOrderPage(OrderStatus.orderStatusToDeliver);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_receive.png', "待收货"),
          onTap: () {
            gotoOrderPage(OrderStatus.orderStatusToReceive);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_comment.png', "待评价"),
          onTap: () {
            gotoOrderPage(OrderStatus.orderStatusToComment);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_return.png', "退换/售后"),
          onTap: () {
            gotoOrderPage(OrderStatus.orderStatusToReturn);
          },
        ),
      ],
    );
  }

  Column buildToOrderBtns(String imageUrl, String text) {
    return Column(
      children: [
        Image(
          image: AssetImage(imageUrl),
          width: 35,
          height: 35,
        ),
        Text(text, style: const TextStyle(fontSize: 15.0, color: Color(0xFF595D63))),
      ],
    );
  }

  Row buildOrderTitleRow() {
    return Row(
      children: [
        const Text("我的订单",
            style: TextStyle(
              fontSize: 18.0,
            )),
        Spacer(),
        InkWell(
          //通过InkWell给控件添加点击事件
          child: Row(
            children: const [
              Text("全部订单", style: TextStyle(fontSize: 15.0, color: Color(0xFF595D63))),
              Image(
                image: AssetImage('assets/images/right_arrow.png'),
                width: 20,
                height: 20,
              )
            ],
          ),
          onTap: () {
            gotoOrderPage(OrderStatus.orderStatusAll);
          },
        ),
      ],
    );
  }

  Row buildSettings() {
    return Row(
      mainAxisAlignment: MainAxisAlignment.end,
      children: [
        IconButton(
          iconSize: 50,
          icon: Image.asset(
            'assets/images/settings.png',
            width: 25,
            height: 25,
          ),
          onPressed: () {
            gotoSettingsPage();
          },
        )
      ],
    );
  }

  void gotoSettingsPage() {}

  void gotoOrderPage(int orderStatus) {
    LoginManager.isLoggedIn().then((value) {
      if (value) {
        Navigator.push(mContext, MaterialPageRoute(builder: (context) => OrderListPage(orderStatus)));
      } else {
        Navigator.push(mContext, MaterialPageRoute(builder: (context) => const LoginPage()));
      }
    });
  }

  void checkLogin() {}
}
