package com.v.nevi.p.sv.android.reaction.exercises.coloredtext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.ColoredTextBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import com.v.nevi.p.sv.android.reaction.exercises.findcolor.Color
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import kotlinx.coroutines.*

private const val MIN_FALSE = 1
private const val MAX_FALSE = 4
private const val MIN_DELAY = 1
private const val MAX_DELAY = 2

class ColoredTextLogic : Logic() {

    private val coroutine = CoroutineScope(Dispatchers.Main.immediate)
    private lateinit var job: Job
    private val colors: MutableList<Color> = mutableListOf<Color>().apply {
        add(Color(R.string.red, R.color.red_500))
        add(Color(R.string.purple, R.color.deep_purple_500))
        add(Color(R.string.pink, R.color.pink_400))
        add(Color(R.string.blue, R.color.indigo_500))
        add(Color(R.string.green, R.color.green_500))
        add(Color(R.string.brown, R.color.brown_500))
        add(Color(R.string.orange, R.color.orange_500))
        add(Color(R.string.grey, R.color.grey_500))
        add(Color(R.string.yellow, R.color.yellow_500))
    }

    private var color: Color? = null

    override fun createData(onDataCreatedCallback: () -> Unit) {
        color = null
        val numberFalse = GenerateRandomNumber.execute(MIN_FALSE, MAX_FALSE)
        job = coroutine.launch {
            for (i in 0 until numberFalse) {
                val indexColor = GenerateRandomNumber.execute(0, colors.size - 1)
                var indexName: Int
                do {
                    indexName = GenerateRandomNumber.execute(0, colors.size - 1)
                } while (indexColor == indexName)
                val colorId = colors[indexColor].colorId
                val nameId = colors[indexName].nameId
                dataLogic.setColorId(colorId)
                dataLogic.setText(nameId)
                val delay = (GenerateRandomNumber.execute(MIN_DELAY, MAX_DELAY) * 1000).toLong()
                delay(delay)
                if (i == numberFalse - 1) {
                    val index = GenerateRandomNumber.execute(0, colors.size - 1)
                    color = colors[index]
                    dataLogic.setText(color!!.nameId)
                    dataLogic.setColorId(color!!.colorId)
                    onDataCreatedCallback.invoke()
                }
            }
        }

    }

    override fun isAnswerCorrect(v: View): Boolean {
        if (job.isActive) {
            job.cancel()
            return false
        }
        if (color == null) {
            return false
        }
        return true
    }

    override var dataLogic: ColoredTextData = ColoredTextData()

    override fun create() {
        dataLogic = ColoredTextData()
    }

    override fun bindData(
        viewModel: ExerciseViewModel,
        inflater: LayoutInflater,
        container: ViewGroup,
        mLifecycleOwner: LifecycleOwner
    ) {
        ColoredTextBinding.inflate(inflater, container, true).apply {
            lifecycleOwner = mLifecycleOwner
            viewmodel = viewModel
            data = dataLogic
        }
    }
}