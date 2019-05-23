package com.video.alivideo;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.multidex.MultiDex;
import com.alivc.live.pusher.LogUtil;
import com.alivc.player.AliVcMediaPlayer;
import com.alivc.player.VcPlayerLog;
import com.aliyun.video.common.aliha.AliHaUtils;

public class APP extends Application {

  public static Context sContext;

  @Override
  protected void attachBaseContext(Context base) {
    super.attachBaseContext(base);
    sContext = this;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    MultiDex.install(this);
    IntentFilter filter = new IntentFilter();
    filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
    registerReceiver(new ConnectivityChangedReceiver(), filter);
    if (BuildConfig.DEBUG) {
      LogUtil.enalbeDebug();
    } else {
      LogUtil.disableDebug();
    }

    VcPlayerLog.enableLog();
    //初始化阿⾥云移动高可⽤SDK接⼊——崩溃分析
    AliHaUtils.initHa(this, null);
    //初始化播放器
    AliVcMediaPlayer.init(getApplicationContext());

  }

  class ConnectivityChangedReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

    }
  }




}
