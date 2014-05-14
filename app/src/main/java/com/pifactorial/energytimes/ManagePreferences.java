package com.pifactorial.energytimes;

import android.annotation.TargetApi;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import android.os.Build;

import android.preference.PreferenceManager;

public class ManagePreferences {

    private final SharedPreferences mPrefs;
    private final Context c;
    private static final String PLAN_SHARED_PREF = "planPreference";
    private final int sdk_version = android.os.Build.VERSION.SDK_INT;

    public ManagePreferences(Context context) {
        c = context;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(c);
    }

    protected String getPlanPreference() {
        return mPrefs.getString(PLAN_SHARED_PREF, "BTN Ciclo Semanal");
    }

    protected String getTypeHourPlan() {
        String mHoursPreference = mPrefs.getString(c.getString(R.string.hours_preference_key), "TRI");
        return mHoursPreference;
    }

    protected String getCompany() {
        String companyName = mPrefs.getString(c.getString(R.string.company_preference_key), "EDP");
        return companyName;
    }

    public void setPlanPreference(String plan) {
        final Editor editor = mPrefs.edit();
        editor.putString(PLAN_SHARED_PREF, plan);
        if(sdk_version < Build.VERSION_CODES.GINGERBREAD) {
            commit(editor);
        } else {
            apply(editor);
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private void commit(Editor e) {
        e.commit();
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private void apply(Editor e) {
        e.apply();
    }
}
