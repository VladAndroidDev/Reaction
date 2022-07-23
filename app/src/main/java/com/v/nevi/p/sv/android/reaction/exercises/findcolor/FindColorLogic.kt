package com.v.nevi.p.sv.android.reaction.exercises.findcolor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.databinding.FindColorBinding
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseViewModel
import com.v.nevi.p.sv.android.reaction.exercises.Logic
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import kotlinx.parcelize.Parcelize
import kotlin.properties.Delegates


class FindColorLogic:Logic() {

    private val colors:MutableList<Color> =  mutableListOf<Color>().apply {
        add(Color(R.string.red,R.color.red_500))
        add(Color(R.string.purple,R.color.deep_purple_500))
        add(Color(R.string.pink,R.color.pink_400))
        add(Color(R.string.blue,R.color.indigo_500))
        add(Color(R.string.green,R.color.green_500))
        add(Color(R.string.brown,R.color.brown_500))
        add(Color(R.string.orange,R.color.orange_500))
        add(Color(R.string.grey,R.color.grey_500))
        add(Color(R.string.yellow,R.color.yellow_500))
    }

    private var lastNameId:Int?=null

    override suspend fun createData(onDataCreatedCallback: () -> Unit) {
        var index:Int
        if(lastNameId==null) {
            index = GenerateRandomNumber.execute(0, colors.size - 1)
        }else{
            do{
                index = GenerateRandomNumber.execute(0,colors.size - 1)
            }while(lastNameId==colors[index].nameId)
        }
        colors.shuffle()
        lastNameId = colors[index].nameId
        dataLogic.setColorNameId(lastNameId!!)
        onDataCreatedCallback.invoke()
    }

    override fun isAnswerCorrect(v: View): Boolean {
        val tag = v.tag as Int
        return tag == lastNameId
    }

    override lateinit var dataLogic: FindColorData

    override fun create() {
        dataLogic = FindColorData()
    }


    override fun bindData(
        viewModel: ExerciseViewModel,
        inflater: LayoutInflater,
        container: ViewGroup,
        mLifecycleOwner: LifecycleOwner
    ) {
        val binding = FindColorBinding.inflate(inflater,container,true).apply {
            lifecycleOwner = mLifecycleOwner
            findColorRecycler.layoutManager = GridLayoutManager(root.context,3)
            findColorRecycler.adapter = FindColorAdapter(viewModel,colors)
            findColorRecycler.setItemViewCacheSize(colors.size)
            findColorRecycler.setHasFixedSize(true)
            findColorRecycler.itemAnimator = null
        }
        dataLogic.colorNameId.observe(mLifecycleOwner){
            binding.color.text = binding.root.context.getText(it)
            binding.findColorRecycler.adapter!!.notifyItemRangeChanged(0,colors.size)
        }
    }
}