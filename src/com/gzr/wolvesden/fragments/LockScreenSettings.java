/*
 * Copyright (C) 2014-2016 The Dirty Unicorns Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.gzr.wolvesden.fragments;

import android.content.Context;
import android.content.ContentResolver;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.Settings;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.Preference.OnPreferenceChangeListener;
import android.support.v14.preference.SwitchPreference;

import com.android.settings.R;
import com.android.settings.SettingsPreferenceFragment;
import com.android.internal.logging.nano.MetricsProto;
import com.android.settings.Utils;

public class LockScreenSettings extends SettingsPreferenceFragment implements
        Preference.OnPreferenceChangeListener {

    private ListPreference mLockClockStyle;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.lockscreen_settings);
        mLockClockStyle = (ListPreference) findPreference("lockscreen_clock_selection");
        mLockClockStyle.setOnPreferenceChangeListener(this);
        int lockClockStyle = Settings.System.getIntForUser(getContentResolver(),
                Settings.System.LOCKSCREEN_CLOCK_SELECTION,
                0, UserHandle.USER_CURRENT);
        mLockClockStyle.setValue(String.valueOf(lockClockStyle));
        mLockClockStyle.setSummary(mLockClockStyle.getEntry());
    } 

    @Override
    public int getMetricsCategory() {
        return MetricsProto.MetricsEvent.VALIDUS;
    }

    @Override
    public void onResume() {
        super.onResume();

    }    

    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ContentResolver resolver = getActivity().getContentResolver();
        if (preference.equals(mLockClockStyle)) {
            int lockClockStyle = Integer.parseInt(((String) newValue).toString());
            Settings.System.putIntForUser(getContentResolver(),
                    Settings.System.LOCKSCREEN_CLOCK_SELECTION, lockClockStyle, UserHandle.USER_CURRENT);
            int index = mLockClockStyle.findIndexOfValue((String) newValue);
            mLockClockStyle.setSummary(
                    mLockClockStyle.getEntries()[index]);
        return true;

    }  
        return false;
    }
}
