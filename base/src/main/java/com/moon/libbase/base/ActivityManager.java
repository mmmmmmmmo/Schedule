package com.moon.libbase.base;

import android.app.Activity;

import java.util.HashSet;

/**
 * @author ry
 * @date 2019-07-15
 */
public class ActivityManager {

    private static ActivityManager instance = new ActivityManager();
    private static HashSet<Activity> hashSet = new HashSet<>();
    private ActivityManager(){

    }
    public static ActivityManager getInstance() {
        return instance;
    }
    public boolean isOnScreen() {
        return hashSet.size() > 0;
    }

    /**
     * 每一个Activity 在 onCreate 方法的时候，可以装入当前this
     * @param activity
     */
    public void addActivity(Activity activity) {
        try {
            hashSet.add(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void removeActivity(Activity activity){
        try {
            hashSet.remove(activity);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void finishSingleActivityByClass(Class<?> cls){
        Activity tempActivity = null;
        for (Activity temp : hashSet){
            if (temp.getClass().equals(cls)){
                tempActivity = temp;
            }
        }
        removeActivity(tempActivity);

    }

    /**
     * 调用此方法用于销毁所有的Activity，然后我们在调用此方法之前，调到登录的Activity
     */
    public void exit() {
        try {
            for (Activity activity : hashSet) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
