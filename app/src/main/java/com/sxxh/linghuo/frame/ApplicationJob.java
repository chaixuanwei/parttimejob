package com.sxxh.linghuo.frame;

import android.app.Application;
import android.content.Context;
import android.os.Build;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;



public class ApplicationJob extends Application {
    private static ApplicationJob mApplication;
    private RefWatcher mRefWatcher;
    public String mToken;

    public static String getUuid() {
        return uuid;
    }

    private static String uuid;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        if (Build.VERSION.SDK_INT >= 28) {
            closeAndroidPDialog();
        }
//        mRefWatcher = setupLeakCanary();
    }

    public static void setUuid(String pUuid){
        uuid = pUuid;
    }

    private RefWatcher setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(this);
    }

    public RefWatcher getRefWatcher() {
        return mRefWatcher;
    }

    public static ApplicationJob getApplication() {
        return mApplication != null ? mApplication : null;
    }

    public static Context getAppContext() {
        return mApplication.getApplicationContext();
    }

    private void closeAndroidPDialog() {
        try {
            Class aClass = Class.forName("android.content.pm.PackageParser$Package");
            Constructor declaredConstructor = aClass.getDeclaredConstructor(String.class);
            declaredConstructor.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Class cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread");
            declaredMethod.setAccessible(true);
            Object activityThread = declaredMethod.invoke(null);
            Field mHiddenApiWarningShown = cls.getDeclaredField("mHiddenApiWarningShown");
            mHiddenApiWarningShown.setAccessible(true);
            mHiddenApiWarningShown.setBoolean(activityThread, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
