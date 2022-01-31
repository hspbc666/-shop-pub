import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'package:shop_flutter/lblbc_constants.dart';
import 'package:shop_flutter/pages/cart.dart';
import 'package:shop_flutter/pages/category.dart';
import 'package:shop_flutter/pages/login/login.dart';
import 'package:shop_flutter/pages/mine.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class MainPage extends StatefulWidget {
  @override
  createState() => _MainPageState();
}

class _MainPageState extends State<MainPage> {
  int _currentIndex = 0;
  List<Widget> list = [];

  @override
  initState() {
    super.initState();
    list
      ..add(const CategoryPage())
      ..add(const CategoryPage())
      ..add(const CartPage())
      ..add(MinePage());
    checkLogin();
  }

  Future<void> checkLogin() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    String? token = sharedPreferences.getString(Constants.spKeyToken);
    if (token == null) {
      Navigator.push(context, MaterialPageRoute(builder: (context) => const LoginPage()));
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: list[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        items: const [
          BottomNavigationBarItem(
              icon: Icon(
                Icons.home,
              ),
              label: '首页'),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.list,
              ),
              label: '分类'),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.shopping_cart,
              ),
              label: '购物车'),
          BottomNavigationBarItem(
              icon: Icon(
                Icons.person,
              ),
              label: '我的'),
        ],
        selectedItemColor: LblColors.mainColor,
        unselectedItemColor: const Color(0xFF222222),
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
