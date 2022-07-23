package com.v.nevi.p.sv.android.reaction.exercises.changecolor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data
import kotlinx.parcelize.Parcelize

open class ChangeColorData : Data() {

    private val _colorId: MutableLiveData<Int> = MutableLiveData()
    val colorId: LiveData<Int> = _colorId

    fun setColorIdAsync(colorId: Int) {
        _colorId.postValue(colorId)
    }

}