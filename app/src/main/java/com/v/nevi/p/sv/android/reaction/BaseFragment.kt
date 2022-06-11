package com.v.nevi.p.sv.android.reaction

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

abstract class BaseFragment:Fragment() {

    protected abstract val viewModel:BaseViewModel

    protected fun setupCloseNavigation(){
        viewModel.onCloseClickEvent.observe(viewLifecycleOwner,EventObserver{
            onCloseClick()
        })
    }

    protected open fun onCloseClick(){
        findNavController().popBackStack()
    }

}