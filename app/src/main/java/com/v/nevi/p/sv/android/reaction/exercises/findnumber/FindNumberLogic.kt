package com.v.nevi.p.sv.android.reaction.exercises.findnumber

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.FindNumberBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber

class FindNumberLogic:Logic() {

    private val numbersId: MutableList<Int> = mutableListOf()

    init {
        numbersId.add(R.string.one)
        numbersId.add(R.string.two)
        numbersId.add(R.string.three)
        numbersId.add(R.string.four)
        numbersId.add(R.string.five)
        numbersId.add(R.string.six)
        numbersId.add(R.string.seven)
        numbersId.add(R.string.eight)
        numbersId.add(R.string.nine)
    }

    private val numbers:MutableList<Int> = (1..9).toMutableList()
    private var lastNumber:Int?=null
    override lateinit var dataLogic:FindNumberData

    override fun create() {
        dataLogic = FindNumberData()
    }

    override suspend fun createData(onDataCreatedCallback: () -> Unit) {
        var index:Int
        if(lastNumber==null) {
            index = GenerateRandomNumber.execute(0, numbersId.size - 1)
        }else{
            do{
                index = GenerateRandomNumber.execute(0,numbersId.size - 1)
            }while(lastNumber==index+1)
        }
        lastNumber = index+1
        val numberId = numbersId[index]
        numbers.shuffle()
        dataLogic.setNumber(numberId)
        onDataCreatedCallback.invoke()
    }

    override fun isAnswerCorrect(v: View): Boolean {
        if(v is TextView){
            if(v.text.toString().toInt() ==  lastNumber){
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
        val binding = FindNumberBinding.inflate(inflater,container,true).apply{
            findNumberRecycler.adapter = FindNumberAdapter(viewModel,numbers)
            findNumberRecycler.layoutManager = GridLayoutManager(findNumberRecycler.context,3)
            findNumberRecycler.itemAnimator = null
            findNumberRecycler.setHasFixedSize(true)
            findNumberRecycler.setItemViewCacheSize(numbersId.size)
            lifecycleOwner = mLifecycleOwner
        }
        dataLogic.number.observe(mLifecycleOwner){
            binding.number.text = binding.number.context.resources.getText(it)
            binding.findNumberRecycler.adapter!!.notifyItemRangeChanged(0,numbersId.size)
        }
    }
}