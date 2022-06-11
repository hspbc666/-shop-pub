import 'package:shared_preferences/shared_preferences.dart';
import 'package:shop_flutter/lblbc_constants.dart';

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

  static logout() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    prefs.remove(Constants.spKeyUserId);
    prefs.remove(Constants.spKeyToken);
  }
}
