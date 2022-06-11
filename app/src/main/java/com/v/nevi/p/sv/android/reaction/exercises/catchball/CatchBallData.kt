package com.v.nevi.p.sv.android.reaction.exercises.catchball

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data
import kotlinx.parcelize.Parcelize

class CatchBallData: Data() {

    private val _isMoving:MutableLiveData<Boolean> = MutableLiveData()
    val isMoving:LiveData<Boolean> = _isMoving

    fun setIsMoving(isMoving:Boolean){
        _isMoving.value = isMoving
    }
}