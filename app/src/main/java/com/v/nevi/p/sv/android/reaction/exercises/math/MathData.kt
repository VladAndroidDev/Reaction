package com.v.nevi.p.sv.android.reaction.exercises.math

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.exercises.Data

class MathData: Data() {

    private val _task: MutableLiveData<String> = MutableLiveData()
    val task: LiveData<String> = _task

    fun setTask(task:String){
        _task.value = task
    }

}