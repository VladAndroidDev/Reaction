package com.v.nevi.p.sv.android.reaction.exercises.catchball

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import com.v.nevi.p.sv.android.reaction.databinding.CatchBallBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import com.v.nevi.p.sv.android.reaction.views.BallView
import kotlinx.coroutines.*
import kotlinx.parcelize.Parcelize

private const val MAX_DELAY = 4
private const val MIN_DELAY = 2

class CatchBallLogic : Logic() {

    private val coroutine = CoroutineScope(Dispatchers.Main.immediate)
    private  var job: Job?=null
    override lateinit var dataLogic:CatchBallData

    override fun create() {
        dataLogic = CatchBallData()
    }

    override fun createData(onDataCreatedCallback: () -> Unit) {
        val time:Long = (GenerateRandomNumber.execute(
            MIN_DELAY,
            MAX_DELAY
        )*1000).toLong()
        job = coroutine.launch {
            delay(time)
            dataLogic.setIsMoving(true)
            onDataCreatedCallback.invoke()
        }
    }

    override fun isAnswerCorrect(v: View): Boolean {
        dataLogic.setIsMoving(false)
        if(job?.isActive == true){
            job!!.cancel()
            return false
        }
        if (v is BallView) {
            if(v.isTouchedBall){
                return true
            }
        }
        return false
    }

    override fun bindData(
        viewModel: ExerciseViewModel,
        inflater: LayoutInflater,
        container: ViewGroup,
        mLifecycleOwner: LifecycleOwner
    ) {
        CatchBallBinding.inflate(inflater, container, true).apply {
            viewmodel = viewModel
            lifecycleOwner = mLifecycleOwner
            data = dataLogic
        }
    }
}