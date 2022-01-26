import 'package:flutter/material.dart';
import 'package:note_flutter/pages/addr/addr_list.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../../constants.dart';
import '../category/note_list.dart';
import '../login.dart';
import '../mine/mine.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class HomeWidget extends StatefulWidget {
  @override
  createState() => _HomeWidgetState();
}

class _HomeWidgetState extends State<HomeWidget> {
  int _currentIndex = 0;
  List<Widget> list = [];

  @override
  initState() {
    super.initState();
    list
      ..add(NoteListPage(context))
      ..add(AddrListPage(context))
      ..add(MinePage());
    checkLogin();
  }

  Future<void> checkLogin() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String? token = sharedPreferences.getString(Constants.SP_KEY_TOKEN);
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
                color: LblColors.mainColor,
              ),
              title: Text(
                '首页',
                style: TextStyle(color: LblColors.mainColor),
              )),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.list,
                color: LblColors.mainColor,
              ),
              title: Text(
                '分类',
                style: TextStyle(color: LblColors.mainColor),
              )),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.shopping_cart,
                color: LblColors.mainColor,
              ),
              title: Text(
                '购物车',
                style: TextStyle(color: LblColors.mainColor),
              )),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.person,
                color: LblColors.mainColor,
              ),
              title: Text(
                '我的',
                style: TextStyle(color: LblColors.mainColor),
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
