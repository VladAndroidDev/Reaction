package com.v.nevi.p.sv.android.reaction.exercises.changecolor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.ChangeColorBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import kotlinx.coroutines.*
import kotlinx.parcelize.Parcelize

private const val MAX_DELAY = 4
private const val MIN_DELAY = 2

class ChangeColorLogic : Logic() {

    private val coroutine = CoroutineScope(Dispatchers.Default)
    private lateinit var job:Job
    private var indexColor=-1
    private val colors = ColorsForGame.colorsId
    override lateinit var dataLogic:ChangeColorData

    override fun create() {
        dataLogic = ChangeColorData()
    }

    override suspend fun createData(onDataCreatedCallback:()->Unit) {
        indexColor=-1
        dataLogic.setColorIdAsync(R.color.white)
        val time:Long = (GenerateRandomNumber.execute(MIN_DELAY, MAX_DELAY)*1000).toLong()
        var newColorIndex:Int
        do {
            newColorIndex = GenerateRandomNumber.execute(0,colors.size-1)
        }while (newColorIndex==indexColor)
        indexColor = newColorIndex
        job = coroutine.launch {
            delay(time)
            dataLogic.setColorIdAsync(colors[indexColor])
            onDataCreatedCallback.invoke()
        }
    }

    override fun isAnswerCorrect(v: View):Boolean {
        return if(job.isActive) {
            job.cancel()
            false
        }else{
            true
        }
    }

    override fun bindData(
        viewModel: ExerciseViewModel,
        inflater: LayoutInflater,
        container: ViewGroup,
        mLifecycleOwner: LifecycleOwner
    ) {
        ChangeColorBinding.inflate(inflater,container,true).apply {
            lifecycleOwner = mLifecycleOwner
            viewmodel = viewModel
            data = dataLogic
        }
    }

}