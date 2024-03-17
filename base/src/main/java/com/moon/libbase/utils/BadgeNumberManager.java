package com.moon.libbase.utils;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class BadgeNumberManager {

    private Context mContext;

    private BadgeNumberManager(Context context) {
        mContext = context;
    }

    public static BadgeNumberManager from(Context context) {
        return new BadgeNumberManager(context);
    }

    private static BadgeNumberManager.Impl IMPL = null;

    /**
     * 设置应用在桌面上显示的角标数字
     *
     * @param number 显示的数字
     */
    public void setBadgeNumber(int number) {
        IMPL.setBadgeNumber(mContext, number);
    }

    interface Impl {
        void setBadgeNumber(Context context, int number);
    }

    static class ImplHuaWei implements Impl {
        @Override
        public void setBadgeNumber(Context context, int number) {
            try {
                if (number < 0) number = 0;
                Bundle bundle = new Bundle();
                bundle.putString("package", context.getPackageName());
                String launchClassName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
                bundle.putString("class", launchClassName);
                bundle.putInt("badgenumber", number);
                context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", null, bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    static class ImplVIVO implements Impl {
        @Override
        public void setBadgeNumber(Context context, int number) {
            try {
                Intent intent = new Intent("launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM");
                intent.putExtra("packageName", context.getPackageName());
                String launchClassName = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName()).getComponent().getClassName();
                intent.putExtra("className", launchClassName);
                intent.putExtra("notificationNum", number);
                context.sendBroadcast(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public static boolean canResolveBroadcast(Context context, Intent intent) {
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> receivers = packageManager.queryBroadcastReceivers(intent, 0);
        return receivers != null && receivers.size() > 0;
    }

    static class ImplOPPO implements Impl {
        @Override
        public void setBadgeNumber(Context context, int number) {
            try {
                if (number == 0) {
                    number = -1;
                }
                Intent intent = new Intent("com.oppo.unsettledevent");
                intent.putExtra("pakeageName", context.getPackageName());
                intent.putExtra("number", number);
                intent.putExtra("upgradeNumber", number);
                if (canResolveBroadcast(context, intent)) {
                    context.sendBroadcast(intent);
                } else {
                    try {
                        Bundle extras = new Bundle();
                        extras.putInt("app_badge_count", number);
                        context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", null, extras);
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class ImplXIAOMI implements Impl {
        @Override
        public void setBadgeNumber(Context context, int number) {
            Log.d("BadgeNumberManager","ImplXIAOMI num="+number);
            String version = System.getProperty("ro.miui.ui.version.name", null);
//            String version = prop.getProperty("ro.miui.ui.version.name", "");

            Log.d("BadgeNumberManager","version = "+version);
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                Notification notification = new Notification.Builder(context, "default")
    //                    .setSmallIcon(R.drawable.notification_icon)
                        .setContentTitle("消息提示")
                        .setContentText("您有" + number + "条未读消息")
                        .setNumber(number)
                        .build();
            }

//            try {
//                Field field = notification.getClass().getDeclaredField("extraNotification");
//                Object extraNotification = field.get(notification);
//                Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
//                method.invoke(extraNotification, number);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }


    static class ImplBase implements Impl {
        @Override
        public void setBadgeNumber(Context context, int number) {
            //do nothing
        }
    }

    static {
        String own = Build.MANUFACTURER;
        Log.d("BadgeNumberManager","manufacturer="+own);
        if (own.equalsIgnoreCase("Huawei")||own.equalsIgnoreCase("HONOR")) {
            IMPL = new ImplHuaWei();
        } else if (own.equalsIgnoreCase("vivo")) {
            IMPL = new ImplVIVO();
        } else if (own.equalsIgnoreCase("oppo")) {
            IMPL = new ImplOPPO();
        } else if (own.equalsIgnoreCase("Xiaomi")) {
            IMPL = new ImplXIAOMI();
        } else {
            IMPL = new ImplBase();
        }
    }
}
