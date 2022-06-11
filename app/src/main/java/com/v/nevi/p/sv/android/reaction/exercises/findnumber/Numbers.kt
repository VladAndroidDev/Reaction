package com.v.nevi.p.sv.android.reaction.exercises.findnumber

import com.v.nevi.p.sv.android.reaction.R

class Numbers {

    companion object {
        private val _numbers: MutableList<Int> = mutableListOf()

        init {
            _numbers.add(R.string.one)
            _numbers.add(R.string.two)
            _numbers.add(R.string.three)
            _numbers.add(R.string.four)
            _numbers.add(R.string.five)
            _numbers.add(R.string.six)
            _numbers.add(R.string.seven)
            _numbers.add(R.string.eight)
            _numbers.add(R.string.nine)
        }

        val numbers: List<Int>
            get() = _numbers
    }
}