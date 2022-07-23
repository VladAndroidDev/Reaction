package com.v.nevi.p.sv.android.reaction.exercises.coloredtext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data
import com.v.nevi.p.sv.android.reaction.exercises.changecolor.ChangeColorData


class ColoredTextData: Data(){
    private val _textId:MutableLiveData<Int> = MutableLiveData()
    val textId:LiveData<Int> = _textId

    private val _colorId: MutableLiveData<Int> = MutableLiveData()
    val colorId: LiveData<Int> = _colorId

    fun setTextAsync(text:Int, color:Int){
        _textId.postValue(text)
        _colorId.postValue(color)
    }

}