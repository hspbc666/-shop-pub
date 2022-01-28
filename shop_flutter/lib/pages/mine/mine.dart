import 'package:flutter/material.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class MinePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Container(
          margin: EdgeInsets.fromLTRB(20, 30, 20, 20),
          child: Column(
            children: [buildSettings(), buildUserInfo()],
          )),
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
        Column(
          children: [
            Text("尊贵会员"),
            Text("级别：白银"),
          ],
        ),
      ],
    );
  }

  void gotoSettingsPage() {}
}
