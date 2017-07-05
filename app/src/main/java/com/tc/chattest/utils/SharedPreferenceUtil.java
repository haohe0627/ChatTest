package com.tc.chattest.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.tc.chattest.MyApplication;

/**
 * Created by haohe on 2017/5/31 0031.
 */

public class SharedPreferenceUtil {

    public static String SP_NAME = "";

    private static void apply(SharedPreferences.Editor editor){
        editor.apply();
        try {
            editor.apply();
        }catch (Exception e){
            editor.commit();
        }
    }

    private static SharedPreferences getPreference(){
        return MyApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public static void set(String key, Object value){
        SharedPreferences.Editor editor = getPreference().edit();
        String v = value.getClass().getSimpleName();
        switch (v){
            case "String":
                editor.putString(key, (String) value);
                break;
            case "Integer":
                editor.putInt(key, (Integer) value);
                break;
            case "Float":
                editor.putFloat(key, (Float) value);
                break;
            case "Boolean":
                editor.putBoolean(key, (Boolean) value);
                break;
            case "Long":
                editor.putLong(key, (Long) value);
                break;
        }
        apply(editor);
    }

    public static Object get(String key, Object defValue){
        String v = defValue.getClass().getSimpleName();
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);

        switch (v){
            case "String":
                return sp.getString(key, (String) defValue);
            case "Integer":
                return sp.getInt(key, (Integer) defValue);
            case "Float":
                return sp.getFloat(key, (Float) defValue);
            case "Boolean":
                return sp.getBoolean(key, (Boolean) defValue);
            case "Long":
                return sp.getLong(key, (Long) defValue);
        }
        return null;
    }

    public static void clean(String[] keys){
        SharedPreferences.Editor editor = getPreference().edit();
        if(keys != null && keys.length>0){
            for( int i = 0; i<keys.length; i++){
                editor.remove(keys[i]);
            }
            editor.clear();
            apply(editor);
        }
    }

}
