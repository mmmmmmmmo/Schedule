package com.moon.libbase.utils.permission;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import timber.log.Timber;

import static com.moon.libbase.utils.permission.PermissionManager.PERMISSION_BATTERY_OPTIMIZATION;
import static com.moon.libbase.utils.permission.PermissionManager.PERMISSION_DRAW_OVERLAYS;
import static com.moon.libbase.utils.permission.PermissionManager.PERMISSION_NOTIFICATION_USAGE;
import static com.moon.libbase.utils.permission.PermissionManager.PERMISSION_PACKAGE_USAGE;
import static com.moon.libbase.utils.permission.PermissionManager.PERMISSION_WRITE_SETTINGS;

/**
 * @author ry
 * @date 2019-12-13
 */
public class StartActivityUtil {

    //应用使用情况权限
    public static final String START_PACKAGE_USAGE = "start.PACKAGE_USAGE";
    //电池优化
    public static final String START_BATTERY_OPTIMIZATION = "start.BATTERY_OPTIMIZATION";
    //通知使用情况
    public static final String START_NOTIFICATION_USAGE = "start.NOTIFICATION_USAGE";
    //悬浮窗权限
    public static final String START_DRAW_OVERLAYS = "start.DRAW_OVERLAYS";
    //系统设置修改权限
    public static final String START_WRITE_SETTINGS = "start.WRITE_SETTINGS";
    //应用设置
    public static final String START_APP_SETTING = "start.APP_SETTING";

    @StringDef({START_PACKAGE_USAGE, START_BATTERY_OPTIMIZATION, START_NOTIFICATION_USAGE,
            START_DRAW_OVERLAYS, START_WRITE_SETTINGS, START_APP_SETTING})
    @Retention(RetentionPolicy.SOURCE)
    public @interface StartType {
    }

    public static boolean startPermissionActivity(@StartType String type, Context context) {
        boolean result = false;
        switch (type) {
            case PERMISSION_PACKAGE_USAGE:
                result = startUsageAccess(context);
                break;
            case PERMISSION_BATTERY_OPTIMIZATION:
                result = startBatteryOptimization(context);
                break;
            case PERMISSION_NOTIFICATION_USAGE:
                result = startNotificationAccessSetting(context);
                break;
            case PERMISSION_DRAW_OVERLAYS:
                result = startDrawOverlays(context);
                break;
            case PERMISSION_WRITE_SETTINGS:
                result = startWriteSettings(context);
                break;
            case START_APP_SETTING:
                result = startAppSetting(context);
                break;
        }
        return result;
    }


    /**
     * 跳转到授权使用权限界面
     */
    public static boolean startUsageAccess(Context context) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Intent intent = new Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS);
            return startSafely(context, intent);
        }
        return false;
    }

    /**
     * 跳转电池优化
     */
    public static boolean startBatteryOptimization(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_REQUEST_IGNORE_BATTERY_OPTIMIZATIONS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            return startSafely(context, intent);
        }
        return false;
    }

    /**
     * 跳转通知使用权
     */
    public static boolean startNotificationAccessSetting(Context context) {
        Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
        return startSafely(context, intent);
    }

    /**
     * 跳转到app详情界面
     */
    public static boolean startAppSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        return startSafely(context, mIntent);
    }

    /**
     * 跳转到悬浮窗权限设置页
     */
    public static boolean startDrawOverlays(Context context) {
        //不同rom的跳转，待测试
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//            if (manageDrawOverlaysForRom(context)) {
//                return;
//            }
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            return startSafely(context, intent);
        }
        return false;
    }


    /**
     * 跳转到系统设置修改权限设置页，暂时只支持 Android 6.0+
     */
    public static boolean startWriteSettings(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
            return startSafely(context, intent);
        }
        return false;
    }

    /**
     * 启动Activity
     */
    public static boolean startSafely(Context context, Intent intent) {
        if (context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY).size() > 0) {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            return true;
        } else {
            Timber.e("Intent is not available! %s", intent.toString());
            return false;
        }
    }

    /**
     * 检测 响应某个意图的Activity 是否存在
     */
    public static boolean isIntentAvailable(Context context, Intent intent) {
        final PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    /*以下是未用工厂模式的跳转*/

    /**
     * 跳转到拨号界面
     */
    public static boolean startCallPhone(Context context, String phone) {
        Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
        return startSafely(context, dialIntent);
    }


    public static boolean startGpsSetting(Context context){
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return startSafely(context,intent);
    }


}
