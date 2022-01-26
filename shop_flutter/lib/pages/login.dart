import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:note_flutter/network/http_manager.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../constants.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class LoginPage extends StatefulWidget {
  LoginPage({Key? key}) : super(key: key);

  @override
  createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  TextEditingController _nameController = TextEditingController();
  TextEditingController _passwordController = TextEditingController();

  String name = "";
  String password = "";

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("登录"),
        leading: IconButton(
          icon: Icon(Icons.arrow_back),
          onPressed: () {
            Navigator.pop(context);
          },
        ),
      ),
      body: Center(
        child: Container(margin: EdgeInsets.fromLTRB(20, 20, 20, 20), child: buildColumn()),
      ),
    );
  }

  Column buildColumn() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.center,
      mainAxisAlignment: MainAxisAlignment.center,
      children: <Widget>[
        Container(
          width: 200,
          child: TextField(
            style: TextStyle(fontSize: 20),
            decoration: InputDecoration(
              hintText: "请输入用户名",
            ),
            controller: _nameController,
          ),
        ),
        Container(
          width: 200,
          child: TextField(
            style: TextStyle(fontSize: 20),
            obscureText: true,
            decoration: InputDecoration(
              hintText: "请输入密码",
            ),
            controller: _passwordController,
          ),
        ),
        Container(
          margin: EdgeInsets.fromLTRB(0.0, 40.0, 0.0, 0.0),
          child: ElevatedButton(
            child: Text("登录"),
            onPressed: () {
              login();
            },
          ),
        ),
      ],
    );
  }

  login() async {
    String url = "api/login";
    HttpManager.getInstance()
        .post(url, data: {"name": _nameController.text, "password": _passwordController.text}).then((resp) async {
      SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
      sharedPreferences.setInt(Constants.SP_KEY_USER_ID, resp['data']['id']);
      sharedPreferences.setString(Constants.SP_KEY_TOKEN, resp['data']['token']);
      Navigator.pop(context);
    });
  }
}
