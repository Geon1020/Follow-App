package geon.follow_app.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {
    public enum PREFS_KEY {
        WALK_COUNT
    }

    public static void setStringPreference(PREFS_KEY key, String value) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key.toString(), value);
        editor.commit();
    }

    public static void setStringPreference(String key, String value) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    //set int value
    public static void setIntPreference(String key, int value) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    //get string value
    public static String getStringPreference(PREFS_KEY key) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String result = pref.getString(key.toString(), null);
        return result;
    }

    //get string value
    public static String getStringPreference(String key, String def) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        String result = pref.getString(key, def);
        return result;
    }

    //set int value
    public static void setIntPreference(PREFS_KEY key, int value) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key.toString(), value);
        editor.commit();
    }

    //get int value
    public static int getIntPreference(String key) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        int result = pref.getInt(key, -1);
        return result;
    }

    //get int value
    public static int getIntPreference(PREFS_KEY key) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        int result = pref.getInt(key.toString(), -1);
        return result;
    }

    //set boolean value
    public static void setBooleanPreference(PREFS_KEY key, boolean value) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key.toString(), value);
        editor.commit();
    }

    //get boolean value
    public static boolean getBooleanPreference(PREFS_KEY key) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean result = pref.getBoolean(key.toString(), false);
        return result;
    }

    //특정 값을 삭제 시
    public static void removeValuePreference(PREFS_KEY key){
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key.toString());
        editor.commit();
    }

    //특정 값을 삭제 시
    public static void removeValuePreference(String key){
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key.toString());
        editor.commit();
    }

    //모든 값 삭제시
    public static void removeAllPreference() {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    // 값 있는지 확인
    public static boolean isContains(String key) {
        SharedPreferences pref = FollowApp.getInstance().getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        boolean result = pref.contains(key);
        return result;
    }
}
