import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:note_flutter/network/http_manager.dart';
import 'package:shared_preferences/shared_preferences.dart';

import '../constants.dart';
import 'add_note.dart';
import 'view_note.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class NoteListPage extends StatelessWidget {
  final parentContext;

  NoteListPage(this.parentContext);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'X商城-蓝不蓝编程',
      home: NoteListWidget(this.parentContext),
    );
  }
}

class NoteListWidget extends StatefulWidget {
  final parentContext;

  NoteListWidget(this.parentContext);

  @override
  createState() => _NoteListState();
}

class _NoteListState extends State<NoteListWidget> {
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
        title: Text("X商城"),
        backgroundColor: LblColors.mainColor,
      ),
      body: Center(
        child: getBody(),
      ),
    );
  }

  gotoAddNotePage() {
    Navigator.push(widget.parentContext, MaterialPageRoute(builder: (context) => AddNotePage()));
  }

  loadData() async {
    SharedPreferences sharedPreferences = await SharedPreferences.getInstance();
    int? userId = sharedPreferences.getInt(Constants.SP_KEY_USER_ID);
    if (userId != null) {
      String url = "note/api/list/" + userId.toString();
      HttpManager.getInstance().get(url).then((resp) {
        Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
        setState(() {
          notes = result['data'];
        });
      });
    }
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
                note['content'],
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
    } else {
      // 加载菊花
      return CupertinoActivityIndicator();
    }
  }

  onRowClick(note) {
    Navigator.push(widget.parentContext, MaterialPageRoute(builder: (context) => ViewNotePage(noteId: note['id'])));
  }
}
