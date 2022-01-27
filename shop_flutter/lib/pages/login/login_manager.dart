import 'package:note_flutter/constants.dart';
import 'package:shared_preferences/shared_preferences.dart';

class LoginManager {
  static Future<bool> isLoggedIn() async {
    SharedPreferences prefs = await SharedPreferences.getInstance();
    int? userId = prefs.getInt(Constants.SP_KEY_USER_ID);
    var result = false;
    if (userId != null) {
      result = true;
    }
    return result;
  }
}
