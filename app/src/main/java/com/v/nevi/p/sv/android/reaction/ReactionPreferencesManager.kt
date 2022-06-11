package com.v.nevi.p.sv.android.reaction

import android.content.Context
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.v.nevi.p.sv.android.reaction.exercises.Exercise
import com.v.nevi.p.sv.android.reaction.exercises.ExerciseUserStory

object ReactionPreferencesManager {

    fun saveExercise(context: Context,exercise: Exercise){
        val userStory = exercise.getUserStory()
        val gson = Gson()
        val str = gson.toJson(userStory)
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = preferenceManager.edit()
        editor.putString(userStory.id.toString(),str)
        editor.apply()
    }

    fun initExercises(context: Context,list: List<Exercise>){
        val gson = Gson()
        val preferenceManager = PreferenceManager.getDefaultSharedPreferences(context)
        for(exercise in list){
            val str = preferenceManager.getString(exercise.id.toString(),null)
            if(str!=null){
                val userStory = gson.fromJson(str,ExerciseUserStory::class.java)
                exercise.setUserStory(userStory)
            }
        }
    }
}