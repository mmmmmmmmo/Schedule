package com.moon.libbase.utils.system;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.widget.Toast;

import androidx.core.content.FileProvider;

import com.moon.libbase.R;
import com.moon.libbase.utils.secret.Md5Util;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.File;
import java.util.Locale;
import java.util.UUID;

import timber.log.Timber;



public class SystemUtils {
    private static String deviceId = "";

    public static String getDeviceId() {
        return deviceId;
    }

    /**
     * 生成设备id
     */
    public static String getDeviceId(Context mContext) {
        if (!TextUtils.isEmpty(deviceId)) {
            return deviceId;
        }
        //设备号
        if (TextUtils.isEmpty(deviceId)) {
            String androidId = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
            String imei = getImsi(mContext);
            UUID deviceUuid = new UUID(androidId.hashCode(), ((long) imei.hashCode() << 32) | imei.hashCode());
            deviceId = Md5Util.generateStr(deviceUuid.toString());
        }
        Timber.d("deviceId = %s", deviceId);
        return deviceId;
    }

    /**
     * 获取imsi码
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getImsi(Context mContext) {
        String imsi = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                imsi = telephonyManager.getSubscriberId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imsi == null ? "" : imsi;
    }

    /**
     * 获取imei码
     */
    @SuppressLint({"MissingPermission", "HardwareIds"})
    public static String getImei(Context mContext) {
        String imei = "";
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                imei = telephonyManager.getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return imei == null ? "" : imei;
    }

    /**
     * 检测Sdcard是否存在
     *
     * @return
     */
    public static boolean isExitsSdcard() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
            return true;
        else
            return false;
    }

    /**
     * 获取app名
     */
    public static String getAppName(Context mContext) {
        return mContext.getResources().getString(getPackageInfo(mContext).applicationInfo.labelRes);
    }

    /**
     * 获取PackageInfo
     */
    public static PackageInfo getPackageInfo(Context mContext) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo;
    }

    public static String getVersion(Context mContext) {
        return "V" + getPackageInfo(mContext).versionName;
    }

    public static String getVersionCode(Context mContext) {
        return "" + getlongVersionCode(mContext);
    }

    public static long getVCode(Context mContext) {
        return getlongVersionCode(mContext);
    }

    public static long getlongVersionCode(Context mContext) {
        long appVersionCode;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            appVersionCode = getPackageInfo(mContext).getLongVersionCode();
        } else {
            appVersionCode = getPackageInfo(mContext).versionCode;
        }
        return appVersionCode;
    }

    /**
     * 安装APK文件
     */
    public static void installApk(Context context, File apkFile) {

        if (checkCompleteness(context, apkFile.getAbsolutePath())) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            Uri uri;
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
                uri = FileProvider.getUriForFile(context, getPackageInfo(context).packageName + ".provider", apkFile);
                //设置临时权限，不然无法安装
                i.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                uri = Uri.fromFile(apkFile);
            }
            // 通过Intent安装APK文件
            i.setDataAndType(uri, "application/vnd.android.package-archive");
            context.startActivity(i);
        } else {
            Toast.makeText(context, R.string.re_download_apk, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 检测apk完整性
     */
    public static boolean checkCompleteness(Context context, String archiveFilePath) {
        boolean result = false;
        try {
            PackageManager pm = context.getPackageManager();
            //获取apk信息
            PackageInfo info = pm.getPackageArchiveInfo(archiveFilePath, PackageManager.GET_ACTIVITIES);
            if (info != null) {
                result = true;
            }
        } catch (Exception e) {
        }
        return result;
    }


    public static boolean isWifiConnected(Context mContext) {
        NetworkInfo mWiFiNetworkInfo = getWifiNetworkInfo(mContext);
        boolean isWifiConnected = false;
        if (mWiFiNetworkInfo != null) {
            isWifiConnected = mWiFiNetworkInfo.isConnected();
        }
        return isWifiConnected;
    }

    public static NetworkInfo getWifiNetworkInfo(Context mContext) {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) mContext
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWiFiNetworkInfo = mConnectivityManager
                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWiFiNetworkInfo;
    }

}
