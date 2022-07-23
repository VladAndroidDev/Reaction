package com.v.nevi.p.sv.android.reaction.exercises

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.v.nevi.p.sv.android.reaction.BaseViewModel
import com.v.nevi.p.sv.android.reaction.Event
import com.v.nevi.p.sv.android.reaction.ReactionPreferencesManager
import kotlinx.coroutines.launch

interface OnProgressChangedSliderListener {

    fun onProgressChangeListener(newValue: Int)

}

class ExerciseViewModel(id: String) : BaseViewModel(), OnProgressChangedSliderListener {

    private var exercise: Exercise = ExercisesRepository.getExerciseById(id)

    val nameExercise: Int
        get() = exercise.nameId

    val descriptionExercise: Int
        get() = exercise.descriptionId

    val minNumberRounds = exercise.minNumberRounds

    var lastNumberRounds = exercise.lastNumberRounds

    private val _numberRounds: MutableLiveData<Int> = MutableLiveData<Int>().apply {
        value = if (exercise.lastNumberRounds == 0) {
            minNumberRounds
        } else {
            lastNumberRounds
        }
    }

    val numberRounds: LiveData<Int> = _numberRounds

    private var counter = 1
    private var numberMistakes = 0

    private val _counterData: MutableLiveData<String> = MutableLiveData(counter.toString())
    val counterData: LiveData<String> = _counterData

    private val _penaltyData: MutableLiveData<Long> = MutableLiveData()
    val penaltyData: LiveData<Long> = _penaltyData

    private fun startPenaltyAnim() {
        _penaltyData.value = exercise.penaltyTime
    }

    private val timer: ExerciseTimer = ExerciseTimer()

    private fun nextRound() {
        viewModelScope.launch {
            exercise.createData {
                timer.start()
            }
        }
    }

    private val _isStarting: MutableLiveData<Event<Boolean>> = MutableLiveData(Event(false))
    val isStarting: LiveData<Boolean> = _isStarting.map {
        it.peekContent()
    }

    private val _onFinishEvent: MutableLiveData<Event<Result>> = MutableLiveData()
    val onFinishEvent: LiveData<Event<Result>> = _onFinishEvent

    private fun finish(averageTime: Long,context: Context) {
        val minimumMistakes = if (numberRounds.value!! - 3 < 0) {
            0
        } else {
            numberRounds.value!! / 5
        }
        if(numberMistakes <= minimumMistakes) {
            if(exercise.bestTime == 0L){
                exercise.bestTime = averageTime
            }else {
                if (averageTime < exercise.bestTime) {
                    exercise.bestTime = averageTime
                }
            }
            if (averageTime <= exercise.minimumTime) {
                val nextExercise = ExercisesRepository.getNextExercise(exercise)
                if (nextExercise != null && !nextExercise.isOpened) {
                    nextExercise.isOpened = true
                    ReactionPreferencesManager.saveExercise(context,nextExercise)
                }
            }
        }
        ReactionPreferencesManager.saveExercise(context,exercise)
        _onFinishEvent.value = Event(
            Result(
                averageTime,
                exercise.minimumTime,
                numberMistakes,
                exercise.nameId,
                minimumMistakes
            )
        )
    }

    fun onAnswerClicked(v: View) {
        if (exercise.onAnswerSelected(v)) {
            timer.stop()
        } else {
            startPenaltyAnim()
            numberMistakes++
            timer.addPenalty(exercise.penaltyTime)
        }
        if (counter == numberRounds.value) {
            finish(timer.averageTime,v.context)
        } else {
            incrementCounter()
            nextRound()
        }
    }

    private fun incrementCounter() {
        counter++
        _counterData.value = counter.toString()
    }

    fun onStartClick() {
        _isStarting.value = Event(true)
        nextRound()
    }

    fun bindData(inflater: LayoutInflater, container: ViewGroup, lifecycleOwner: LifecycleOwner) {
        exercise.bindData(this, inflater, container, lifecycleOwner)
    }

    override fun onProgressChangeListener(newValue: Int) {
        exercise.lastNumberRounds = newValue
        lastNumberRounds = newValue
        _numberRounds.value = newValue
    }

    fun restart() {
        exercise = ExercisesRepository.getExerciseById(exercise.id)
        _isStarting.value = Event(false)
        counter = 1
        _counterData.value = counter.toString()
        numberMistakes = 0
        timer.restart()
    }
}