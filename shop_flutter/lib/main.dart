import 'package:flutter/material.dart';
import 'package:shop_flutter/constants.dart';

import 'pages/home/home.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
void main() {
  runApp(new MyApp());}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'X商城-蓝不蓝编程',
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        primarySwatch: LblColors.themeColor,
      ),
      home: MainPage(),
    );
  }
}
