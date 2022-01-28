import 'dart:ui';

import 'package:flutter/material.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class Constants {
  static const String spKeyToken = "token";
  static const String spKeyUserId = "user_id";
  static const String httpHeaderAuth = "Authorization";
  static const String httpHeaderTokenPrefix = "Bearer ";
}

class LblColors {
  static const mainColor = Color(0xFFEF3965);
  static const themeColor = MaterialColor(
    0xFFEF3965,
    <int, Color>{
      50: Color(0xFFFFEBEE),
      100: Color(0xFFFFCDD2),
      200: Color(0xFFEF9A9A),
      300: Color(0xFFE57373),
      400: Color(0xFFEF5350),
      500: Color(0xFFEF3965),
      600: Color(0xFFE53935),
      700: Color(0xFFD32F2F),
      800: Color(0xFFC62828),
      900: Color(0xFFB71C1C),
    },
  );
// static const mainColor = Color(0xFFEF3965);
}
