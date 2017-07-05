package com.lulu.lin.mac;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

/**
 * Created by lin on 2017/6/28.
 */

public class MyApplication extends Application {
    private static final String TAG = MyApplication.class.getSimpleName();
    private boolean needKill=false;
    @Override
    public void onCreate() {
        super.onCreate();
        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            SophixManager.getInstance().setContext(this)
                    .setAppVersion(info.versionName)
                    .setAesKey(null)
                    .setEnableDebug(true)
                    .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                        @Override
                        public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                            // 补丁加载回调通知
                            if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                                // 表明补丁加载成功
                                Log.d(TAG,"CODE_LOAD_SUCCESS");
                            } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                                // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                                // 建议: 用户可以监听进入后台事件, 然后应用自杀
                                Log.d(TAG,"CODE_LOAD_RELAUNCH");
                                needKill=true;
                            } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                                // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                                // SophixManager.getInstance().cleanPatches();
                                Log.d(TAG,"CODE_LOAD_FAIL");
                            } else {
                                // 其它错误信息, 查看PatchStatus类说明
                                Log.d(TAG,"other error");
                            }
                        }
                    }).initialize();
            SophixManager.getInstance().queryAndLoadNewPatch();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        ForegroundCallbacks.get(this).addListener(new ForegroundCallbacks.Listener() {
            @Override
            public void onBecameForeground() {

            }

            @Override
            public void onBecameBackground() {
                if (needKill){
                    final Context application = MyApplication.this;
                    Intent launchIntent = application.getPackageManager().getLaunchIntentForPackage(application.getPackageName());
                    if (launchIntent != null) {
                        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        application.startActivity(launchIntent);
                        SophixManager.getInstance().killProcessSafely();
                    }

                }
            }
        });


    }
}
