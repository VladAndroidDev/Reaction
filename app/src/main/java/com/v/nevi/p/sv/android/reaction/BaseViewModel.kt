package com.v.nevi.p.sv.android.reaction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel:ViewModel() {

    private val _onCloseClickEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onCloseClickEvent:LiveData<Event<Unit>> = _onCloseClickEvent

    fun onCloseClick(){
        _onCloseClickEvent.value = Event(Unit)
    }

}