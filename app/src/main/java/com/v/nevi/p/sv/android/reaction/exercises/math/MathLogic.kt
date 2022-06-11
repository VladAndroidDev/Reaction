package com.v.nevi.p.sv.android.reaction.exercises.math

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.v.nevi.p.sv.android.reaction.databinding.MathBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import java.lang.Exception

class MathLogic : Logic() {

    private val list: MutableList<String> = mutableListOf<String>().apply {
        add("+")
        add("-")
        add("×")
        add("÷")
    }

    private var previousOperation: Int? = null
    private lateinit var operationStr: String

    override fun createData(onDataCreatedCallback: () -> Unit) {
        var operation: Int
        if (previousOperation == null) {
            operation = GenerateRandomNumber.execute(0, 3)
        } else {
            do {
                operation = GenerateRandomNumber.execute(0, 3)
            } while (operation == previousOperation)
        }
        previousOperation = operation
        val res: Int
        val task: String = when (operation) {
            0 -> {
                val firstValue = GenerateRandomNumber.execute(1, 20)
                var secondValue: Int
                do {
                    secondValue = GenerateRandomNumber.execute(1, 20)
                } while (firstValue == secondValue)
                res = firstValue + secondValue
                operationStr = "+"
                "$firstValue _ $secondValue = $res"
            }
            1 -> {
                val firstValue = GenerateRandomNumber.execute(1, 20)
                var secondValue: Int
                do {
                    secondValue = GenerateRandomNumber.execute(1, 20)
                } while (firstValue == secondValue)
                res = firstValue - secondValue
                operationStr = "-"
                "$firstValue _ $secondValue = $res"
            }
            2 -> {
                val firstValue = GenerateRandomNumber.execute(0, 10)
                val secondValue = GenerateRandomNumber.execute(2, 10)
                res = firstValue * secondValue
                operationStr = "×"
                "$firstValue _ $secondValue = $res"
            }
            3 -> {
                var firstValue: Int
                do {
                    firstValue = GenerateRandomNumber.execute(10, 30)
                } while (firstValue % 2 != 0 || firstValue % 3 != 0)
                var secondValue: Int
                do {
                    secondValue = GenerateRandomNumber.execute(2, 15)
                } while (firstValue == secondValue || firstValue % secondValue != 0)
                res = firstValue / secondValue
                operationStr = "÷"
                "$firstValue _ $secondValue = $res"
            }
            else -> {
                throw Exception("not created task")
            }
        }
        list.shuffle()
        dataLogic.setTask(task)
        onDataCreatedCallback.invoke()
    }

    override fun isAnswerCorrect(v: View): Boolean {
        if (v is TextView) {
            return v.text == operationStr
        }
        return false
    }

    override var dataLogic: MathData = MathData()

    override fun create() {
        dataLogic = MathData()
    }

    override fun bindData(
        viewModel: ExerciseViewModel,
        inflater: LayoutInflater,
        container: ViewGroup,
        mLifecycleOwner: LifecycleOwner
    ) {
        val binding = MathBinding.inflate(inflater, container, true).apply {
            mathRecycler.layoutManager = GridLayoutManager(root.context, 2)
            mathRecycler.adapter = MathAdapter(viewModel, list)
            mathRecycler.itemAnimator = null
        }
        dataLogic.task.observe(mLifecycleOwner) {
            binding.mathTask.text = it
            binding.mathRecycler.adapter!!.notifyItemRangeChanged(0, list.size)
        }
    }
}