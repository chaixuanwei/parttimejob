package com.sxxh.linghuo.local_utils;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

import com.sxxh.linghuo.config.Config;
import com.sxxh.linghuo.frame.ApplicationJob;
import com.switfpass.pay.utils.MD5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class NetHeaders {
    public static Map getHeadMap() {
        String uuid = !TextUtils.isEmpty(ApplicationJob.getUuid()) ? ApplicationJob.getUuid() : getLocalUUIDX(ApplicationJob.getAppContext());
        Map<String, String> headers = new HashMap<>();
        headers.put("XX-Api-Version", getAppVersionCode(ApplicationJob.getAppContext()));
        headers.put("XX-Device-Type", "android");
        String mToken = SharedPrefrenceUtils.getString(ApplicationJob.getAppContext(), Config.TOKEN);
        if (mToken != null) {
            headers.put("XX-Token",mToken);
        }
        return headers;
    }

    public static String getUserAgent() {
        int versionCode = 1;
        try {
            versionCode = ApplicationJob.getApplication().getPackageManager().getPackageInfo(ApplicationJob.getApplication().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException pE) {
            pE.printStackTrace();
        }
        String webviewAgent = SharedPrefrenceUtils.getString(ApplicationJob.getAppContext(), Config.USER_AGENT, "");
        return webviewAgent + " News/" + versionCode + " Android/"
                + versionCode + " NewsApp/" + versionCode + " SDK/"
                + Build.VERSION.SDK_INT + " VERSION/"
                + getVersionName();
    }

    public static String getVersionName() {
        try {
            PackageManager manager = ApplicationJob.getAppContext().getPackageManager();
            PackageInfo info = manager
                    .getPackageInfo(ApplicationJob.getAppContext().getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unknown";
    }

    @SuppressLint("MissingPermission")
    public static String getLocalUUIDX(Context context) {
        String uuid = !TextUtils.isEmpty(ApplicationJob.getUuid()) ? ApplicationJob.getUuid() : SharedPrefrenceUtils.getString(context, Config.UUIDX, "");
        if (!TextUtils.isEmpty(uuid)) {
            return uuid;
        }
        String deviceId = null;
        String simNumber = null;
        try {
            Object o = context.getSystemService(Context.TELEPHONY_SERVICE);
            if (o != null && o instanceof TelephonyManager) {
                TelephonyManager tm = (TelephonyManager) o;
                if (tm != null) {
                    deviceId = tm.getDeviceId();
                    simNumber = tm.getSimSerialNumber();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String macAddress = null;
        try {
            WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            if (wifi != null && wifi.getConnectionInfo() != null) {
                macAddress = wifi.getConnectionInfo().getMacAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String androidId = null;
        try {
            androidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String serialNumber = null;
        try {
            serialNumber = Build.SERIAL;
        } catch (Exception e) {
            e.printStackTrace();
        }

        String bluetoothAddress = null;
        try {
            BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
            if (adapter != null) {
                bluetoothAddress = adapter.getAddress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (TextUtils.isEmpty(deviceId))
            deviceId = "";

        if (TextUtils.isEmpty(simNumber))
            simNumber = "";

        if (TextUtils.isEmpty(macAddress))
            macAddress = "";

        if (TextUtils.isEmpty(androidId))
            androidId = "";

        if (TextUtils.isEmpty(serialNumber))
            serialNumber = "";

        if (TextUtils.isEmpty(bluetoothAddress))
            bluetoothAddress = "";

        uuid = MD5.getMessageDigest((deviceId + simNumber + macAddress).getBytes())
                + MD5.getMessageDigest((androidId + serialNumber + bluetoothAddress).getBytes());
        if (TextUtils.isEmpty(uuid)) {
            uuid = getUUIDXCache(context);
            if (TextUtils.isEmpty(uuid)) {
                String t = UUID.randomUUID().toString();
                uuid = MD5.getMessageDigest(t.getBytes());
            }
        }

        try {
            uuid = "@" + MD5Util.encryptDES(uuid + "zQcN6aR4");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPrefrenceUtils.saveString(context,Config.UUIDX,uuid);
        return uuid;
    }

    public static String getUUIDXCache(Context context) {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
            if (file != null) {
                file = new File(file.getAbsolutePath(), ".uuid.x");
            }

            if (file != null && file.exists()) {
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    byte[] bt = new byte[1024];
                    int length = fis.read(bt);
                    String uuid = new String(bt, 0, length);
                    if (!TextUtils.isEmpty(uuid) && uuid.length() == 32)
                        return uuid;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (fis != null)
                        try {
                            fis.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                }
            }
        }
        return null;
    }

    public static String getAppVersionCode(Context context) {
        int versioncode = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            // versionName = pi.versionName;
            versioncode = pi.versionCode;
        } catch (Exception e) {
            Log.e("VersionInfo", "Exception", e);
        }
        return versioncode + "";
    }
}
