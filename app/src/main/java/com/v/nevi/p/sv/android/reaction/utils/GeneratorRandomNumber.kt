package com.v.nevi.p.sv.android.reaction.utils

import kotlin.random.Random

class GenerateRandomNumber {
    companion object {
        fun execute(start: Int, end: Int): Int {
            require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" }
            return Random(System.nanoTime()).nextInt(end - start + 1) + start
        }
    }
}