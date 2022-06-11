package com.v.nevi.p.sv.android.reaction.exercises.numbercomparison

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.ComparisonNumbersBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import kotlinx.parcelize.Parcelize
import kotlin.properties.Delegates

private const val START_RANGE = 20
private const val END_RANGE = 80

class ComparisonNumbersLogic : Logic(){

    private var isFirstValueBiggest by Delegates.notNull<Boolean>()
    override lateinit var dataLogic: ComparisonNumbersData

    override fun create() {
        dataLogic = ComparisonNumbersData()
    }


    override fun createData(onDataCreatedCallback: () -> Unit) {
        val firstValue = GenerateRandomNumber.execute(START_RANGE, END_RANGE)
        var secondValue:Int
        do {
            secondValue = GenerateRandomNumber.execute(firstValue-20,firstValue+20)
        }while (firstValue==secondValue)
        isFirstValueBiggest = firstValue > secondValue
        dataLogic.setValues(firstValue.toString(), secondValue.toString())
        onDataCreatedCallback.invoke()
    }

    override fun isAnswerCorrect(v: View): Boolean {
        return (v.id == R.id.first_value && isFirstValueBiggest) || (v.id != R.id.first_value && !isFirstValueBiggest)
    }

    override fun bindData(
        viewModel: ExerciseViewModel,
        inflater: LayoutInflater,
        container: ViewGroup,
        mLifecycleOwner: LifecycleOwner
    ) {
        val binding = ComparisonNumbersBinding.inflate(inflater,container,true).apply {
            viewmodel = viewModel
            lifecycleOwner = mLifecycleOwner
            data = dataLogic
        }
    }

}