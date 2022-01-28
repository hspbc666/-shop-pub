import 'package:shop_flutter/constants.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginManager {
  static Future<bool> isLoggedIn() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    int? userId = prefs.getInt(Constants.spKeyUserId);
    var result = false;
    if (userId != null) {
      result = true;
    }
    return result;
  }
}
