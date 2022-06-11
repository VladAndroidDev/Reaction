package com.v.nevi.p.sv.android.reaction.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.v.nevi.p.sv.android.reaction.R

class Settings : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}