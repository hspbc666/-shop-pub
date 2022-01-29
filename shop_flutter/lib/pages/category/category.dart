import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/network/http_manager.dart';
import 'package:shop_flutter/pages/login/login_manager.dart';

import '../../constants.dart';
import '../add_note.dart';
import '../view_note.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class CategoryPage extends StatefulWidget {
  const CategoryPage({Key? key}) : super(key: key);

  @override
  createState() => _NoteListState();
}

class _NoteListState extends State<CategoryPage> {
  List notes = [];

  @override
  void initState() {
    super.initState();
    loadData();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("X商城23"),
        backgroundColor: LblColors.mainColor,
      ),
      body: Center(
        child: getBody(),
      ),
    );
  }

  gotoAddNotePage() {
    Navigator.push(context, MaterialPageRoute(builder: (context) => AddNotePage()));
  }

  loadData() {
    LoginManager.isLoggedIn().then((value) {
      if (value) {
        String url = "shop/cart/list";
        HttpManager.getInstance().get(url).then((resp) {
          Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
          setState(() {
            notes = result['data'];
          });
        });
      }
    });
  }

  getItem(note) {
    var row = Container(
      margin: EdgeInsets.all(4.0),
      child: InkWell(
        onTap: () {
          onRowClick(note);
        },
        child: buildRow(note),
      ),
    );
    return Card(
      child: row,
    );
  }

  Row buildRow(note) {
    return Row(
      children: <Widget>[
        Expanded(
            child: Container(
          margin: EdgeInsets.only(left: 8.0),
          height: 40.0,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              Text(
                note['name'],
                style: TextStyle(
                  fontSize: 18.0,
                ),
                maxLines: 1,
              ),
            ],
          ),
        ))
      ],
    );
  }

  getBody() {
    if (notes.length != 0) {
      return ListView.builder(
          itemCount: notes.length,
          itemBuilder: (BuildContext context, int position) {
            return getItem(notes[position]);
          });
    }
  }

  onRowClick(note) {
    Navigator.push(context, MaterialPageRoute(builder: (context) => ViewNotePage(noteId: note['id'])));
  }
}
