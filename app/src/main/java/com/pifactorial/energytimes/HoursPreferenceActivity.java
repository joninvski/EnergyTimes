package com.pifactorial.energytimes;

import android.content.res.Resources;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.preference.Preference;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.github.machinarius.preferencefragment.PreferenceFragment;

public class HoursPreferenceActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void onResume() {
        super.onResume();
        FragmentManager mFragmentManager =  getSupportFragmentManager();

        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        UserPreferenceFragment mPrefsFragment = new UserPreferenceFragment();

        mFragmentTransaction.add(android.R.id.content, mPrefsFragment);
        mFragmentTransaction.commit();
    }

    // Fragment that displays the preference
    public static class UserPreferenceFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener {

        protected static final String TAG = "UserPrefsFragment";
        private ManagePreferences mPrefs;
        private Preference mHoursPreference;
        private Preference mCompanyPreference;

        // These fields have to be outside the OnSharedPreferenceChangeListener as it runs on a different thread
        private String mhour_preference_key;
        private String mcompany_preference_key;
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

            mPrefs = new ManagePreferences(getActivity());
        }

        @Override
        public void onResume() {
            super.onResume();
            getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
            updateTypeHour();
            updateChoosenCompany();
        }

        @Override
        public void onPause() {
            super.onPause();
            getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            updateTypeHour();
            updateChoosenCompany();
        }

        private void updateTypeHour() {
            String choosenTypeHour = mPrefs.getTypeHourPlan();
            mHoursPreference.setSummary((new TypeHourEnum(choosenTypeHour)).getHumanString(res));
        }

        private void updateChoosenCompany() {
            String choosenCompany = mPrefs.getCompany();
            mCompanyPreference.setSummary(choosenCompany);
        }
    }
}
