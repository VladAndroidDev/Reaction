package com.v.nevi.p.sv.android.reaction.exercises.coloredtext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.changecolor.ChangeColorData


class ColoredTextData: ChangeColorData(){
    private val _textId:MutableLiveData<Int> = MutableLiveData()
    val textId:LiveData<Int> = _textId

    fun setText(text:Int){
        _textId.value = text
    }

}