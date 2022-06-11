package com.v.nevi.p.sv.android.reaction.exercises

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner


class Exercise(val id:String, val nameId:Int, val descriptionId:Int, val smallDescription:Int, val minimumTime:Long, private val logic: Logic,
               val minNumberRounds:Int,
               val penaltyTime:Long = 1000,
               var isOpened: Boolean = false) {

    var bestTime:Long=0
    var lastNumberRounds:Int = 0

    fun createData(onDataCreatedCallback:()->Unit){
        logic.createData(onDataCreatedCallback)
    }

    fun onAnswerSelected(v: View):Boolean{
        return logic.isAnswerCorrect(v)
    }

    fun bindData(viewModel: ExerciseViewModel, inflater: LayoutInflater, container:ViewGroup, lifecycleOwner: LifecycleOwner){
        logic.bindData(viewModel,inflater,container, lifecycleOwner)
    }

    fun recreateData(){
        logic.create()
    }

    fun getUserStory():ExerciseUserStory{
        return ExerciseUserStory(id, bestTime, lastNumberRounds, isOpened)
    }

    fun setUserStory(userStory: ExerciseUserStory){
        bestTime = userStory.bestTime
        lastNumberRounds = userStory.lastNumberRounds
        isOpened = userStory.isOpened
    }

    override fun equals(other: Any?): Boolean {
        if(other == null){
            return false
        }
        if(other is Exercise && other.id == id){
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + nameId
        result = 31 * result + descriptionId
        result = 31 * result + smallDescription
        result = 31 * result + minimumTime.hashCode()
        result = 31 * result + minNumberRounds
        result = 31 * result + penaltyTime.hashCode()
        result = 31 * result + isOpened.hashCode()
        result = 31 * result + bestTime.hashCode()
        result = 31 * result + lastNumberRounds
        return result
    }
}
class ExerciseUserStory(val id: String,val bestTime:Long,val lastNumberRounds: Int,val isOpened: Boolean)
