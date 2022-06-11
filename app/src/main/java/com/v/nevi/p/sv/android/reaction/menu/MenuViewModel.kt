package com.v.nevi.p.sv.android.reaction.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.v.nevi.p.sv.android.reaction.Event
import com.v.nevi.p.sv.android.reaction.exercises.Exercise

class MenuViewModel:ViewModel() {

    private val _onItemClickEvent:MutableLiveData<Event<String>> = MutableLiveData()
    val onItemClickEvent:LiveData<Event<String>> = _onItemClickEvent

    private val _startDialogEvent:MutableLiveData<Event<String>> = MutableLiveData()
    val startDialogEvent:LiveData<Event<String>> = _startDialogEvent

    private fun startDialog(id: String){
        _startDialogEvent.value = Event(id)
    }

    fun onItemClick(exercise: Exercise) {
        if (!exercise.isOpened) {
            startDialog(exercise.id)
        } else {
            exercise.recreateData()
            _onItemClickEvent.value = Event(exercise.id)
        }
    }

    private val _onSettingsClickEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onSettingsClickEvent:LiveData<Event<Unit>> = _onSettingsClickEvent

    fun onSettingsClick(){
        _onSettingsClickEvent.value = Event(Unit)
    }
}