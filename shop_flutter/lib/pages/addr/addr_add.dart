import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:shop_flutter/constants.dart';
import 'package:shop_flutter/network/bean/query_user_addr_list_resp_entity.dart';
import 'package:shop_flutter/network/http_manager.dart';

/// 厦门大学计算机专业 | 前华为工程师
/// 专注《零基础学编程系列》https://cxyxy.blog.csdn.net/article/details/121134634
/// 包含：Java | 安卓 | 前端 | Flutter | iOS | 小程序 | 鸿蒙
/// 公众号：蓝不蓝编程

class AddAddrPage extends StatefulWidget {
  const AddAddrPage({Key? key}) : super(key: key);

  @override
  createState() => _AddAddrState();
}

class _AddAddrState extends State<AddAddrPage> {
  QueryUserAddrListRespData userAddrData = QueryUserAddrListRespData();
  var _addrType = 0;
  var _defaultAddress = true;
  var nameController = TextEditingController();
  var phoneController = TextEditingController();
  var regionController = TextEditingController();
  var addressController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("添加收货地址"),
        backgroundColor: LblColors.mainColor,
        actions: [
          IconButton(
              onPressed: () {
                addAddr();
              },
              icon: const Icon(Icons.done))
        ],
      ),
      body: Container(
          margin: const EdgeInsets.fromLTRB(20, 20, 20, 0),
          child: SingleChildScrollView(
            child: Column(
              children: [
                TextField(
                  decoration: const InputDecoration(hintText: "请输入收货人真实姓名", labelText: "收货人"),
                  controller: nameController,
                ),
                TextField(
                    decoration: const InputDecoration(hintText: "请输入收货人手机号", labelText: "手机号"),
                    controller: phoneController,
                    keyboardType: TextInputType.number),
                TextField(
                  decoration: const InputDecoration(hintText: "请输入所在地区", labelText: "所在地区"),
                  controller: regionController,
                ),
                TextField(
                  decoration: const InputDecoration(
                    hintText: "请输入详细地址",
                    labelText: "详细地址",
                  ),
                  controller: addressController,
                ),
                Row(
                  children: [
                    Text("地址类型"),
                    buildAddrTypeRadio(1),
                    const Text("家庭"),
                    buildAddrTypeRadio(2),
                    Text("公司"),
                    buildAddrTypeRadio(3),
                    const Text("其他")
                  ],
                ),
                Row(
                  children: [Text("设为默认地址"), Spacer(), _newSwitch()],
                )
              ],
            ),
          )),
    );
  }

  buildAddrTypeRadio(int value) {
    return Radio(
        value: value,
        groupValue: _addrType,
        activeColor: Colors.blue,
        onChanged: (v) {
          setState(() {
            _addrType = v as int;
            print(_addrType);
          });
        });
  }

  _newSwitch() {
    return Switch(
      value: _defaultAddress,
      activeColor: Colors.white,
      activeTrackColor: Colors.green,
      inactiveThumbColor: Colors.white,
      inactiveTrackColor: Colors.grey,
      onChanged: (value) {
        setState(() {
          _defaultAddress = value;
        });
      },
    );
  }

  Container buildBottomRow() {
    return Container(
      margin: const EdgeInsets.only(bottom: 15),
      width: double.infinity,
      child: SizedBox(
        child: ElevatedButton(
          child: const Text('确定'),
          onPressed: () {
            addAddr();
          },
          style: ButtonStyle(backgroundColor: MaterialStateProperty.all(Color(0xFFEF3965))),
        ),
      ),
    );
  }

  void addAddr() {
    String url = "shop/addr/add";
    userAddrData.name = nameController.value.text;
    userAddrData.phone = phoneController.value.text;
    userAddrData.region = regionController.value.text;
    userAddrData.address = addressController.value.text;
    userAddrData.addrType = _addrType;
    userAddrData.defaultAddress = _defaultAddress;
    HttpManager.getInstance().post(url, data: userAddrData.toJson()).then((resp) {
      Navigator.pop(context);
    });
  }
}
