import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/network/bean/query_user_addr_list_resp.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/addr/addr_add.dart';
import 'package:shop_flutter/pages/addr/addr_edit.dart';
import 'package:shop_flutter/pages/login/login_manager.dart';
import 'package:shop_flutter/ui_kit.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class AddrListPage extends StatefulWidget {
  const AddrListPage({Key? key}) : super(key: key);

  @override
  createState() => _AddrListState();
}

class _AddrListState extends State<AddrListPage> {
  List<UserAddr> dataList = [];

  @override
  void initState() {
    super.initState();
    queryData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("收货地址"),
      ),
      body: Container(
          margin: const EdgeInsets.fromLTRB(5, 20, 10, 0),
          child: Column(
            children: [Expanded(child: buildAddrInfoList()), buildBottomRow()],
          )),
    );
  }

  Container buildBottomRow() {
    return Container(
      margin: const EdgeInsets.only(bottom: 15),
      width: double.infinity,
      child: SizedBox(
        child: ElevatedButton(
          child: const Text('添加收货地址'),
          onPressed: () {
            gotoAddAddrPage();
          },
          style: ButtonStyle(backgroundColor: MaterialStateProperty.all(Color(0xFFEF3965))),
        ),
      ),
    );
  }

  getItem(UserAddr userAddrData) {
    var row = Container(
      margin: const EdgeInsets.all(4.0),
      child: buildRow(userAddrData),
    );
    return Card(
      child: row,
    );
  }

  Row buildRow(UserAddr userAddrData) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Container(
          padding: const EdgeInsets.only(top: 5, bottom: 10),
          child: Row(
            crossAxisAlignment: CrossAxisAlignment.center,
            children: <Widget>[
              Expanded(
                  child: Container(
                margin: const EdgeInsets.only(left: 10),
                child: buildAddrInfoCol(userAddrData),
              ))
            ],
          ),
        ))
      ],
    );
  }

  Column buildAddrInfoCol(UserAddr userAddrData) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        buildUserInfoRow(userAddrData),
        mySpacer(10),
        Text(userAddrData.address),
        mySpacer(10),
        buildDivider(),
        Row(
          children: [
            Spacer(),
            OutlinedButton(
              child: const Text('删除', style: TextStyle(color: Color(0xFF575E64))),
              onPressed: () {
                deleteAddress(userAddrData.id);
              },
            ),
            myVerticalSpacer(10),
            OutlinedButton(
              child: const Text('编辑', style: TextStyle(color: Color(0xFF575E64))),
              onPressed: () {
                gotoEditAddrPage(userAddrData.id);
              },
            )
          ],
        )
      ],
    );
  }

  Row buildUserInfoRow(UserAddr userAddrData) {
    if (userAddrData.defaultAddress) {
      return buildDefaultUserInfoRow(userAddrData);
    } else {
      return buildNonDefaultUserInfoRow(userAddrData);
    }
  }

  Row buildDefaultUserInfoRow(UserAddr userAddrData) {
    return Row(
      children: [
        Text(userAddrData.name),
        myVerticalSpacer(10),
        Text(
          userAddrData.phone,
          style: const TextStyle(color: Color(0xFF999999)),
        ),
        myVerticalSpacer(10),
        buildDefaultAddrButton(),
      ],
    );
  }

  Row buildNonDefaultUserInfoRow(UserAddr userAddrData) {
    return Row(
      children: [
        Text(userAddrData.name),
        myVerticalSpacer(10),
        Text(
          userAddrData.phone,
          style: const TextStyle(color: Color(0xFF999999)),
        ),
        myVerticalSpacer(10),
      ],
    );
  }

  TextButton buildDefaultAddrButton() {
    return TextButton(
      onPressed: () {},
      child: Text('默认', style: const TextStyle(color: Colors.white)),
      style: ButtonStyle(
        backgroundColor: MaterialStateProperty.all(Color(0xFF8298bd)),
      ),
    );
  }

  buildAddrInfoList() {
    if (dataList.isNotEmpty) {
      return ListView.builder(
        itemCount: dataList.length,
        itemBuilder: (BuildContext context, int position) {
          return getItem(dataList[position]);
        },
      );
    } else {
      return emptyContainer();
    }
  }

  void gotoAddAddrPage() {
    Navigator.push(context, MaterialPageRoute(builder: (context) => const AddAddrPage()))
        .then((value) => {queryData()});
  }

  void gotoEditAddrPage(String userAddrId) {
    Navigator.push(
        context,
        MaterialPageRoute(
            builder: (context) => EditAddrPage(
                  userAddrId: userAddrId,
                ))).then((value) => {queryData()});
  }

  void deleteAddress(String userAddrId) {
    String url = "shop/addr/del/" + userAddrId;
    HttpManager.getInstance().get(url).then((resp) {
      queryData();
    });
  }

  queryData() async {
    LoginManager.isLoggedIn().then((value) {
      if (value) {
        String url = "shop/addr/query";
        HttpManager.getInstance().get(url).then((resp) {
          var result = QueryUserAddrListResp.fromJson(resp);
          setState(() {
            dataList = result.data;
          });
        });
      }
    });
  }
}
