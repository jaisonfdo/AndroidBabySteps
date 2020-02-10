package com.droidmentor.androidbabysteps.util.storage;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;

/**
 * Created by Jaison.
 */
public class LocalData {

    public static final String VERSION_NAME = "VERSION_NAME";
    public static final String IS_INITIAL_LAUNCH = "IS_INITIAL_LAUNCH";
    public static final String APP_UPDATE_COUNT = "APP_UPDATE_COUNT";
    private static final String APP_SHARED_PREF_FILE_NAME = "AndroidBabyStepsPref";
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public LocalData(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(APP_SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void setValue(String keyName, Object value) {

        this.prefsEditor = appSharedPrefs.edit();
        if (value instanceof String)
            prefsEditor.putString(keyName, String.valueOf(value));
        else if (value instanceof Float)
            prefsEditor.putFloat(keyName, (Float) value);
        else if (value instanceof Boolean)
            prefsEditor.putBoolean(keyName, (Boolean) value);
        else if (value instanceof Integer)
            prefsEditor.putInt(keyName, (Integer) value);
        else if (value instanceof Long)
            prefsEditor.putLong(keyName, (Long) value);
        else
            throw new UnsupportedOperationException("Invalid object type");

        prefsEditor.apply();
    }

    public <T> T getValue(String keyName, Class<?> valueType) {
        return getValue(keyName, valueType, null);
    }

    public <T> T getValue(String keyName, Class<?> valueType, @Nullable T defaultValue) {

        Object value;

        if (valueType.equals(String.class)) {
            value = appSharedPrefs.getString(keyName, defaultValue == null ? "" : (String) defaultValue);
        } else if (valueType.equals(Float.class)) {
            value = appSharedPrefs.getFloat(keyName, defaultValue == null ? 0.0f : (Float) defaultValue);
        } else if (valueType.equals(Boolean.class)) {
            value = appSharedPrefs.getBoolean(keyName, defaultValue == null ? false : (Boolean) defaultValue);
        } else if (valueType.equals(Integer.class)) {
            value = appSharedPrefs.getInt(keyName, defaultValue == null ? 0 : (Integer) defaultValue);
        } else if (valueType.equals(Long.class)) {
            value = appSharedPrefs.getLong(keyName, defaultValue == null ? 0 : (Long) defaultValue);
        } else {
            throw new UnsupportedOperationException("Not yet implemented.");
        }
        return (T) value;
    }

    public void removeValue(String keyName) {
        this.prefsEditor = appSharedPrefs.edit();
        prefsEditor.remove(keyName).apply();
    }

    public void reset() {
        prefsEditor.clear().commit();
    }

}
