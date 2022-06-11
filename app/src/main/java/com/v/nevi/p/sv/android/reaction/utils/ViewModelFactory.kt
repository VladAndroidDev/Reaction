package com.v.nevi.p.sv.android.reaction.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Result
import com.v.nevi.p.sv.android.reaction.result.ResultViewModel

class ViewModelFactory(private val id: String? = null, private val result: Result?=null):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T = with(modelClass){
        return when{
            isAssignableFrom(ExerciseViewModel::class.java) -> ExerciseViewModel(id!!) as T
            isAssignableFrom(ResultViewModel::class.java)->ResultViewModel(result!!) as T
            else -> throw Exception("View model not found")
        }
    }
}