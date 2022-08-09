package com.ssslzgn.sakura;

import android.app.Application;

import com.alibaba.android.arouter.launcher.ARouter;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 日志开启
        ARouter.openLog();
        // 调试模式开启，如果在install run模式下运行，则必须开启调试模式
        ARouter.openDebug();
        ARouter.init(this);
    }
}
