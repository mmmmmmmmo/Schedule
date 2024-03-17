package com.moon.libbase.utils.permission;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.app.usage.UsageStats;
import android.app.usage.UsageStatsManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.provider.Settings;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import static android.content.Context.POWER_SERVICE;
import static com.moon.libbase.utils.permission.SettingCompat.OP_SYSTEM_ALERT_WINDOW;
import static com.moon.libbase.utils.permission.SettingCompat.OP_WRITE_SETTINGS;

/**
 * @author ry
 * @date 2019-11-06
 */
public class PermissionManager {
    //应用使用情况权限
    public static final String PERMISSION_PACKAGE_USAGE = "permission.PACKAGE_USAGE";
    //电池优化
    public static final String PERMISSION_BATTERY_OPTIMIZATION = "permission.BATTERY_OPTIMIZATION";
    //通知使用情况
    public static final String PERMISSION_NOTIFICATION_USAGE = "permission.NOTIFICATION_USAGE";
    //悬浮窗权限
    public static final String PERMISSION_DRAW_OVERLAYS = "permission.DRAW_OVERLAYS";
    //系统设置修改权限
    public static final String PERMISSION_WRITE_SETTINGS = "permission.WRITE_SETTINGS";

    @StringDef({PERMISSION_PACKAGE_USAGE, PERMISSION_BATTERY_OPTIMIZATION, PERMISSION_NOTIFICATION_USAGE,
            PERMISSION_DRAW_OVERLAYS, PERMISSION_WRITE_SETTINGS})
    @Retention(RetentionPolicy.SOURCE)
    public @interface PermissionType {
    }

    public static boolean checkPermission(@PermissionType String type, Context context) {
        boolean result = false;
        switch (type) {
            case PERMISSION_PACKAGE_USAGE:
                result = checkPackageUsage(context);
                break;
            case PERMISSION_BATTERY_OPTIMIZATION:
                result = checkBatteryOptimization(context);
                break;
            case PERMISSION_NOTIFICATION_USAGE:
                result = notificationListenerEnable(context);
                break;
            case PERMISSION_DRAW_OVERLAYS:
                result = canDrawOverlays(context);
                break;
            case PERMISSION_WRITE_SETTINGS:
                result = canWriteSettings(context);
                break;
        }
        return result;
    }

    /**
     * 检测使用情况权限
     */
    private static boolean checkPackageUsage(Context context) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {   // 如果大于等于5.0 再做判断
            long ts = System.currentTimeMillis();
            UsageStatsManager usageStatsManager = (UsageStatsManager) context.getSystemService(Service.USAGE_STATS_SERVICE);
            List<UsageStats> queryUsageStats = usageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_BEST, 0, ts);
            return queryUsageStats != null && !queryUsageStats.isEmpty();
        }
        return true;
    }

    /**
     * 检测是否已忽略电池优化
     *
     * @return true 已优化
     */
    private static boolean checkBatteryOptimization(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);
            return powerManager != null && powerManager.isIgnoringBatteryOptimizations(context.getPackageName());
        }
        //小于api 23以下，直接返回true。
        return true;
    }

    /**
     * 是否开启了通知使用权
     */
    private static boolean notificationListenerEnable(Context context) {
        boolean enable = false;
        String packageName = context.getPackageName();
        String flat = Settings.Secure.getString(context.getContentResolver(), "enabled_notification_listeners");
        if (flat != null) {
            enable = flat.contains(packageName);
        }
        return enable;
    }

    /**
     * 判断是否有悬浮窗权限
     */
    @SuppressLint("ObsoleteSdkInt")
    public static boolean canDrawOverlays(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return SettingCompat.checkOp(context, OP_SYSTEM_ALERT_WINDOW);
        } else {
            return true;
        }
    }


    /**
     * 判断是否有系统设置修改权限
     */
    @SuppressLint("ObsoleteSdkInt")
    public static boolean canWriteSettings(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.System.canWrite(context);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
            return SettingCompat.checkOp(context, OP_WRITE_SETTINGS);
        } else {
            return true;
        }
    }

    public static boolean hasLocationEnablePermission(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        int locationMode = Settings.Secure.LOCATION_MODE_OFF;
        try {
            locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Exception ignored) {
        }
        return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    }

}
