package com.v.nevi.p.sv.android.reaction.exercises.catchcolor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.SimpleItemAnimator
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.CatchColorBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import kotlinx.coroutines.*

private const val MAX_DELAY = 4
private const val MIN_DELAY = 2

class CatchColorLogic:Logic() {

    private val coroutineScope = CoroutineScope(Dispatchers.Default)

    private val listColors:MutableList<Int> = mutableListOf()
    init {
        for(i in 1..16){
            listColors.add(R.color.white)
        }
    }

    private var oldIndex:Int?=null
    private var newIndex:Int?=null
    private var time:Long?=null
    private var job:Job?=null

    override suspend fun createData(onDataCreatedCallback: () -> Unit) {
        if (oldIndex == null) {
            newIndex = GenerateRandomNumber.execute(0, listColors.size - 1)
        } else {
            listColors[oldIndex!!] = R.color.white
            do {
                newIndex = GenerateRandomNumber.execute(0, listColors.size - 1)
            } while (newIndex == oldIndex)
        }
        val isRed = GenerateRandomNumber.execute(0, 9) == 0
        //val isRed = true
        if (isRed) {
            listColors[newIndex!!] = R.color.red_500
            time = GenerateRandomNumber.execute(MIN_DELAY, MAX_DELAY)*1000.toLong()
        } else {
            listColors[newIndex!!] = R.color.green_500
        }
        dataLogic.setOnDataCreate()
        if (isRed) {
            job = coroutineScope.launch {
                delay(time!!)
                createData(onDataCreatedCallback)
            }
        }else{
            onDataCreatedCallback.invoke()
        }
    }

    override fun isAnswerCorrect(v: View): Boolean {
        if(job?.isActive==true){
            job!!.cancel()
            return false
        }
        val tag = v.tag as Int
        return tag==R.color.green_500
    }

    override lateinit var dataLogic: CatchColorData

    override fun create() {
        dataLogic = CatchColorData()
    }


    override fun bindData(
        viewModel: ExerciseViewModel,
        inflater: LayoutInflater,
        container: ViewGroup,
        mLifecycleOwner: LifecycleOwner
    ) {
        val binding = CatchColorBinding.inflate(inflater,container,true).apply {
            lifecycleOwner = mLifecycleOwner
            catchColorRecycler.layoutManager = GridLayoutManager(root.context,4)
            catchColorRecycler.adapter = CatchColorAdapter(viewModel,listColors)
            (catchColorRecycler.itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
        dataLogic.onDataCreated.observe(mLifecycleOwner){
            if(oldIndex!=null) {
                binding.catchColorRecycler.adapter?.notifyItemChanged(oldIndex!!)
            }
            binding.catchColorRecycler.adapter?.notifyItemChanged(newIndex!!)
            oldIndex=newIndex
        }
    }
}