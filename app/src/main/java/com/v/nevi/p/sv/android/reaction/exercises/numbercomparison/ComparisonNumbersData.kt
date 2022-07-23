package com.v.nevi.p.sv.android.reaction.exercises.numbercomparison

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data
import kotlinx.parcelize.Parcelize

class ComparisonNumbersData : Data(){

    private val _firstValue: MutableLiveData<String> = MutableLiveData()
    val firstValue: LiveData<String> = _firstValue

    private val _secondValue: MutableLiveData<String> = MutableLiveData()
    val secondValue: LiveData<String> = _secondValue

    fun setValues(firstValue: String,secondValue: String){
        _firstValue.postValue(firstValue)
        _secondValue.postValue(secondValue)
    }

}


