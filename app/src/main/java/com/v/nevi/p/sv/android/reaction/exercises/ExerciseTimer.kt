package com.v.nevi.p.sv.android.reaction.exercises

class ExerciseTimer {

    private var startTime: Long = 0
    val averageTime: Long
        get() = sum / count

    private var sum: Long = 0
    private var count = 0

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun stop() {
        count++
        sum += System.currentTimeMillis() - startTime
    }

    fun addPenalty(penaltyTime: Long) {
        count++
        sum += penaltyTime
    }

    fun restart() {
        sum = 0
        count = 0
    }
}