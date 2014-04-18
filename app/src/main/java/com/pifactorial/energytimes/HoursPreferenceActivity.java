package com.pifactorial.energytimes;

import android.app.Activity;
import android.os.Bundle;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceFragment;

public class HoursPreferenceActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager mFragmentManager = getFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        UserPreferenceFragment mPrefsFragment = new UserPreferenceFragment();
        mFragmentTransaction.replace(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
    }

    // Fragment that displays the username preference
    public static class UserPreferenceFragment extends PreferenceFragment {

        protected static final String TAG = "UserPrefsFragment";
        protected static final String USERNAME = "UserPrefsFragment";
        private SharedPreferences.OnSharedPreferenceChangeListener mListener;
        private Preference mHoursPreference;
        private Preference mCompanyPreference;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Load the preferences from an XML resource
            addPreferencesFromResource(R.xml.options_preference_screen);

            // Get the username Preference
            mHoursPreference = getPreferenceManager().findPreference(getString(R.string.hours_preference_key));
            mCompanyPreference = getPreferenceManager().findPreference(getString(R.string.company_preference_key));

            // Attach a listener to update summary when username changes
            mListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
            	@Override
            	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            		mHoursPreference.setSummary(sharedPreferences.getString(getString(R.string.hours_preference_key), "None Set"));
            		mCompanyPreference.setSummary(sharedPreferences.getString(getString(R.string.company_preference_key), "None Set"));
            	}
            };

            // Get SharedPreferences object managed by the PreferenceManager for this Fragment
            SharedPreferences prefs = getPreferenceManager().getSharedPreferences();

            // Register a listener on the SharedPreferences object
            prefs.registerOnSharedPreferenceChangeListener(mListener);

            // Invoke callback manually to display the current username
            mListener.onSharedPreferenceChanged(prefs, getString(R.string.company_preference_key));
        }
    }
}
