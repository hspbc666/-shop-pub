import 'package:flutter/material.dart';
import 'package:shop_flutter/constants.dart';
import 'package:shop_flutter/ui_kit.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class MinePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
          margin: const EdgeInsets.fromLTRB(20, 30, 20, 20),
          child: Column(
            children: [
              buildSettings(),
              buildUserInfo(),
              Container(
                margin: const EdgeInsets.only(top: 40),
                padding: const EdgeInsets.all(10),
                decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.all(Radius.circular(5.0)), //设置圆角矩形背景
                ),
                child: Column(
                  children: [buildOrderTitleRow(), mySpacer(10), buildOrderBtnRow()],
                ),
              ),
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
            gotoOrderPage(OrderTab.orderTabToPay);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_deliver.png', "待发货"),
          onTap: () {
            gotoOrderPage(OrderTab.orderTabToDeliver);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_receive.png', "待收货"),
          onTap: () {
            gotoOrderPage(OrderTab.orderTabToReceive);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_comment.png', "待评价"),
          onTap: () {
            gotoOrderPage(OrderTab.orderTabToComment);
          },
        ),
        const Spacer(),
        InkWell(
          child: buildToOrderBtns('assets/images/to_return.png', "退换/售后"),
          onTap: () {
            gotoOrderPage(OrderTab.orderTabToReturn);
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
            gotoOrderPage(OrderTab.orderTabAll);
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
            width: 30,
            height: 30,
          ),
          onPressed: () {
            gotoSettingsPage();
          },
        )
      ],
    );
  }

  buildUserInfo() {
    return Row(
      children: <Widget>[
        const Image(
          image: AssetImage('assets/images/user.png'),
          width: 50,
          height: 50,
        ),
        Container(
          padding: EdgeInsets.only(left: 20),
          child: Column(
            children: const [
              Text("尊贵会员",
                  style: TextStyle(
                    fontSize: 18.0,
                  )),
              Text("级别：白银"),
            ],
          ),
        ),
      ],
    );
  }

  void gotoSettingsPage() {}

  void gotoOrderPage(int tab) {}
}
