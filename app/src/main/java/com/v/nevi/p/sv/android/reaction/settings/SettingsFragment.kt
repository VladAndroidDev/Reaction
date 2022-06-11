package com.v.nevi.p.sv.android.reaction.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.v.nevi.p.sv.android.reaction.BaseFragment
import com.v.nevi.p.sv.android.reaction.databinding.FragmentSettingsBinding

class SettingsFragment: BaseFragment() {

    override val viewModel:SettingsViewModel by viewModels()

    private lateinit var binding:FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingsBinding.inflate(inflater,container,false).apply {
            viewmodel = viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupCloseNavigation()
    }
}