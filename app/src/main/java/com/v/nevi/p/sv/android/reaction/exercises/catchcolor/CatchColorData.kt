package com.v.nevi.p.sv.android.reaction.exercises.catchcolor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data
import kotlinx.parcelize.Parcelize

class CatchColorData: Data() {

    private val _onDataCreated:MutableLiveData<Boolean> = MutableLiveData()
    val onDataCreated: LiveData<Boolean> = _onDataCreated

    fun setOnDataCreate(){
        _onDataCreated.postValue(true)
    }
}