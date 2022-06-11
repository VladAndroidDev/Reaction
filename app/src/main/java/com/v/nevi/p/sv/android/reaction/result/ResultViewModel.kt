package com.v.nevi.p.sv.android.reaction.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.v.nevi.p.sv.android.reaction.BaseViewModel
import com.v.nevi.p.sv.android.reaction.Event
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.exercises.Result

class ResultViewModel(private val result: Result) : BaseViewModel() {

    val exerciseNameId:Int
    get() = result.exerciseNameId

    val numberMistakes:String
    get() = result.numberMistakes.toString()

    val minimumMistakes:String
    get() = " "+result.minimumMistakes

    val time:String
    get() {
        if(result.time>=10000){
            return "10K+"
        }
        return result.time.toString()
    }

    val minimumTimeForPassing:String
    get() = " "+result.minimumTime

    val isPassed:Boolean
    get() {
        return result.time<=result.minimumTime && result.numberMistakes<=result.minimumMistakes
    }

    val resultDescription:Int
    get() {
        if(result.time>result.minimumTime){
            return R.string.many_time
        }
        if(result.numberMistakes>result.minimumMistakes){
            return R.string.many_mistakes
        }
        return R.string.сongratulations
    }

    private val _onRepeatClickEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onRepeatClickEvent:LiveData<Event<Unit>> = _onRepeatClickEvent

    fun onRepeatClick(){
        _onRepeatClickEvent.value = Event(Unit)
    }

    private val _onMenuClickEvent:MutableLiveData<Event<Unit>> = MutableLiveData()
    val onMenuClickEvent:LiveData<Event<Unit>> = _onMenuClickEvent

    fun onMenuClick(){
        _onMenuClickEvent.value = Event(Unit)
    }
}