package br.com.ecp.flutterandroidimei;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.telephony.TelephonyManager;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterAndroidImeiPlugin */
public class FlutterAndroidImeiPlugin implements MethodCallHandler {

  private Registrar registrar;
  private TelephonyManager telephonyManager;

  private FlutterAndroidImeiPlugin(Registrar registrar){
    telephonyManager = (TelephonyManager) registrar.activity().getSystemService(Context.TELEPHONY_SERVICE);
  }
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_android_imei");
    channel.setMethodCallHandler(new FlutterAndroidImeiPlugin(registrar));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getImei")) {
      result.success(getImei(telephonyManager));
    } else {
      result.notImplemented();
    }
  }

  @SuppressLint("MissingPermission")
  String getImei(TelephonyManager telephonyManager) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      return telephonyManager.getImei();
    } else {
      return telephonyManager.getDeviceId();
    }
  }
}
