import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/network/http_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程
class EditNotePage extends StatefulWidget {
  final int noteId;

  EditNotePage({Key? key, required this.noteId}) : super(key: key);

  @override
  createState() => _EditNotePageState(noteId);
}

class _EditNotePageState extends State<EditNotePage> {
  String content = "";
  int _noteId = 0;

  TextEditingController _contentController = TextEditingController();

  _EditNotePageState(int noteId) {
    _noteId = noteId;
  }

  @override
  void initState() {
    super.initState();
    queryData();
  }

  queryData() async {
    String url = "note/api/query/$_noteId";
    HttpManager.getInstance().get(url).then((resp) {
      Map<String, dynamic> result = new Map<String, dynamic>.from(resp);
      setState(() {
        var data = result['data'];
        content = data['content'];
        _contentController.text = content;
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
        Expanded(
            child: TextField(
          keyboardType: TextInputType.multiline,
          maxLines: 10,
          decoration: InputDecoration(
            hintText: "请输入内容",
          ),
          controller: _contentController,
        )),
        ElevatedButton(
          child: Text("保存"),
          onPressed: () {
            modifyNote();
          },
        ),
      ],
    );
  }

  modifyNote() async {
    String url = "note/api/modify";
    String content = _contentController.text;
    HttpManager.getInstance().post(url, data: {"id": widget.noteId, "content": content}).then((resp) {
      Navigator.of(context).popUntil((route) => route.isFirst); //回到首页
    });
  }
}
