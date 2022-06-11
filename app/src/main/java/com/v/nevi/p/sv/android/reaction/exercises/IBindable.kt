package com.v.nevi.p.sv.android.reaction.exercises

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

interface IBindable {

    fun bindData(viewModel:ExerciseViewModel,inflater: LayoutInflater, container:ViewGroup, mLifecycleOwner: LifecycleOwner)

}