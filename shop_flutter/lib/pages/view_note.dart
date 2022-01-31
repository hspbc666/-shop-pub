import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

import '/network/http_manager.dart';
import '/pages/edit_note.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class ViewNotePage extends StatefulWidget {
  final int noteId;

  ViewNotePage({Key? key, required this.noteId}) : super(key: key);

  @override
  createState() => _ViewNotePageState(noteId);
}

class _ViewNotePageState extends State<ViewNotePage> {
  String content = "";
  int _noteId = 0;

  _ViewNotePageState(int noteId) {
    _noteId = noteId;
  }

  @override
  void initState() {
    super.initState();
    _queryData();
  }

  _queryData() async {
    String url = "note/api/query/$_noteId";
    HttpManager.getInstance().get(url).then((resp) {
      Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
      setState(() {
        var data = result['data'];
        content = data['content'];
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
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
        Expanded(child: Text(content)),
        ElevatedButton(
          child: Text("修改"),
          onPressed: () {
            Navigator.push(context, MaterialPageRoute(builder: (context) => EditNotePage(noteId: _noteId)));
          },
        ),
      ],
    );
  }
}
