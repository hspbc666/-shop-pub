import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';

import 'app_strings.dart';
import 'pages/login.dart';
import 'pages/mine.dart';
import 'pages/note_list.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class HomeWidget extends StatefulWidget {
  @override
  createState() => _HomeWidgetState();
}

class _HomeWidgetState extends State<HomeWidget> {
  final bottomNavigationColor = Color(0xFFEF3965);
  int _currentIndex = 0;
  List<Widget> list = [];

  @override
  initState() {
    super.initState();
    list
      ..add(NoteListPage(context))
      ..add(MinePage());
    checkLogin();
  }

  Future<void> checkLogin() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String? token = sharedPreferences.getString(AppStrings.SP_KEY_TOKEN);
    if (token == null) {
      Navigator.push(context, MaterialPageRoute(builder: (context) => LoginPage()));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: list[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        items: [
          BottomNavigationBarItem(
              icon: Icon(
                Icons.home,
                color: bottomNavigationColor,
              ),
              title: Text(
                '首页',
                style: TextStyle(color: bottomNavigationColor),
              )),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.list,
                color: bottomNavigationColor,
              ),
              title: Text(
                '分类',
                style: TextStyle(color: bottomNavigationColor),
              )),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.shopping_cart,
                color: bottomNavigationColor,
              ),
              title: Text(
                '购物车',
                style: TextStyle(color: bottomNavigationColor),
              )),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.person,
                color: bottomNavigationColor,
              ),
              title: Text(
                '我的',
                style: TextStyle(color: bottomNavigationColor),
              )),
        ],
        unselectedItemColor: Color(0xFF222222),
        type: BottomNavigationBarType.fixed,
        currentIndex: _currentIndex,
        onTap: (int index) {
          setState(() {
            _currentIndex = index;
          });
        },
      ),
    );
  }
}
