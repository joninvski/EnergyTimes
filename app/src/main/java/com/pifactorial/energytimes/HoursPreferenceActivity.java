package com.pifactorial.energytimes;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.content.res.Resources;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.preference.Preference;
import android.preference.PreferenceFragment;

import com.pifactorial.energytimes.TypeHourEnum;
import android.content.Intent;


public class HoursPreferenceActivity extends Activity {

    String hourValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        UserPreferenceFragment mPrefsFragment = new UserPreferenceFragment();
        mFragmentTransaction.add(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
    }

    // Fragment that displays the preference
    public class UserPreferenceFragment extends PreferenceFragment {

        protected static final String TAG = "UserPrefsFragment";
        private SharedPreferences.OnSharedPreferenceChangeListener mListener;
        private Preference mHoursPreference;
        private Preference mCompanyPreference;

        // These fields have to be outside the OnSharedPreferenceChangeListener as it runs on a different thread
        String mhour_preference_key;
        String mcompany_preference_key;
        Resources res;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.options_preference_screen);

            mHoursPreference = getPreferenceManager().findPreference(getString(R.string.hours_preference_key));
            mCompanyPreference = getPreferenceManager().findPreference(getString(R.string.company_preference_key));

            mhour_preference_key = getString(R.string.hours_preference_key);
            mcompany_preference_key = getString(R.string.company_preference_key);
            res = getResources();

            mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
                @Override
                public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                    hourValue = sharedPreferences.getString(mhour_preference_key, "None Set");
                    mHoursPreference.setSummary((new TypeHourEnum(hourValue)).getHumanString(res));
                    mCompanyPreference.setSummary(sharedPreferences.getString(mcompany_preference_key, "None Set"));
                }
            };

            // Get SharedPreferences object managed by the PreferenceManager for this Fragment
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

            // Register a listener on the SharedPreferences object
            prefs.registerOnSharedPreferenceChangeListener(mListener);

            // Invoke callback manually to display the current options
            mListener.onSharedPreferenceChanged(prefs, getString(R.string.company_preference_key));
        }

    }

    @Override
    public void onBackPressed(){
        Intent data = new Intent();
        data.putExtra("mhour", hourValue);
        setResult(RESULT_OK, data);
        finish();
    }
}
