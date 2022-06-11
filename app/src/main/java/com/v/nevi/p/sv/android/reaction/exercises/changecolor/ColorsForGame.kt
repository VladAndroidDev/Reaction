package com.v.nevi.p.sv.android.reaction.exercises.changecolor

import com.v.nevi.p.sv.android.reaction.R

class ColorsForGame {
    companion object{
        private val _colorsId:MutableList<Int> = mutableListOf<Int>().apply {
            add(R.color.red_500)
            add(R.color.deep_purple_500)
            add(R.color.pink_500)
            add(R.color.indigo_500)
            add(R.color.green_500)
            add(R.color.light_blue_500)
            add(R.color.orange_500)
        }
        val colorsId:List<Int>
        get() = _colorsId
    }
}