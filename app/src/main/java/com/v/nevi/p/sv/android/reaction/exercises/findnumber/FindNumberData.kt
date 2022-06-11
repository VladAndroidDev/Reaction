package com.v.nevi.p.sv.android.reaction.exercises.findnumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data
import kotlinx.parcelize.Parcelize


class FindNumberData: Data(){

    private val _number: MutableLiveData<Int> = MutableLiveData()
    val number: LiveData<Int> = _number

    fun setNumber(number:Int){
        _number.value = number
    }

}