import 'dart:async';

import 'package:flutter/services.dart';

class FlutterAndroidImei {
  static const MethodChannel _channel = const MethodChannel('flutter_android_imei');

  static Future<String> get getImei async {
    return await _channel.invokeMethod('getImei');
  }
}
