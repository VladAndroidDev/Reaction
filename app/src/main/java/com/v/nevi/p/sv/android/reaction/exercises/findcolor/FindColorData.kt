package com.v.nevi.p.sv.android.reaction.exercises.findcolor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data
import kotlinx.parcelize.Parcelize


class FindColorData:Data() {

    private val _colorNameId:MutableLiveData<Int> = MutableLiveData()
    val colorNameId:LiveData<Int> = _colorNameId

    fun setColorNameId(colorNameId:Int){
        _colorNameId.postValue(colorNameId)
    }

}