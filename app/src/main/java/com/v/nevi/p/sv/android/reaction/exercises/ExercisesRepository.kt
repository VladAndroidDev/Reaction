package com.v.nevi.p.sv.android.reaction.exercises

import android.content.Context
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.ReactionPreferencesManager
import com.v.nevi.p.sv.android.reaction.exercises.catchball.CatchBallLogic
import com.v.nevi.p.sv.android.reaction.exercises.catchcolor.CatchColorLogic
import com.v.nevi.p.sv.android.reaction.exercises.changecolor.ChangeColorLogic
import com.v.nevi.p.sv.android.reaction.exercises.coloredtext.ColoredTextLogic
import com.v.nevi.p.sv.android.reaction.exercises.findcolor.FindColorLogic
import com.v.nevi.p.sv.android.reaction.exercises.findnumber.FindNumberLogic
import com.v.nevi.p.sv.android.reaction.exercises.math.MathLogic
import com.v.nevi.p.sv.android.reaction.exercises.numbercomparison.ComparisonNumbersLogic
import java.lang.Exception

object ExercisesRepository {

    fun initialize(context: Context){
        ReactionPreferencesManager.initExercises(context,listExercises)
    }

    private val _listExercises: MutableList<Exercise> = mutableListOf()
    val listExercises: List<Exercise> = _listExercises

    init {
        _listExercises.add(
            Exercise(
                id="change-color",
                nameId = R.string.change_color,
                descriptionId = R.string.change_color_description,
                smallDescription = R.string.change_color_small_desc,
                minimumTime = 400,
                logic = ChangeColorLogic(),
                minNumberRounds = 5,
                isOpened = true
            )
        )
        _listExercises.add(
            Exercise(
                id="find-number",
                nameId = R.string.find_number,
                descriptionId = R.string.find_number_description,
                smallDescription = R.string.find_number_small_desc,
                minimumTime = 910,
                logic = FindNumberLogic(),
                minNumberRounds = 5
            )
        )
        _listExercises.add(
            Exercise(
                id="catch-ball",
                nameId = R.string.catch_ball,
                descriptionId = R.string.catch_ball_description,
                smallDescription = R.string.catch_ball_desc,
                minimumTime = 520,
                logic = CatchBallLogic(),
                minNumberRounds = 5
            )
        )
        _listExercises.add(
            Exercise(
                id="comparison-numbers",
                nameId = R.string.comparison_numbers,
                descriptionId = R.string.comparison_numbers_description,
                smallDescription = R.string.comparison_numbers_desc,
                minimumTime = 620,
                logic = ComparisonNumbersLogic(),
                minNumberRounds = 5
            )
        )
        _listExercises.add(
            Exercise(
                id="find-color",
                nameId = R.string.find_color,
                descriptionId = R.string.find_color_description,
                smallDescription = R.string.find_color_desc,
                minimumTime = 730,
                logic = FindColorLogic(),
                minNumberRounds = 5
            )
        )
        _listExercises.add(
            Exercise(
                id="catch-color",
                nameId = R.string.catch_color,
                descriptionId = R.string.catch_color_description,
                smallDescription = R.string.catch_color_desc,
                minimumTime = 470,
                logic = CatchColorLogic(),
                minNumberRounds = 10
            )
        )
        _listExercises.add(
            Exercise(
            id = "colored-text",
            nameId = R.string.colored_text,
            descriptionId = R.string.colored_text_description,
            smallDescription = R.string.colored_text_desc,
            minimumTime = 820,
            logic = ColoredTextLogic(),
            minNumberRounds = 5
        )
        )
        _listExercises.add(
            Exercise(
                id = "math",
                nameId = R.string.math,
                descriptionId = R.string.math_description,
                smallDescription = R.string.math_desc,
                minimumTime = 1400,
                logic = MathLogic(),
                minNumberRounds = 5
            )
        )
    }

    fun hasNotOpenedExercises():Boolean{
        for(exercise in listExercises){
            if(!exercise.isOpened){
                return true
            }
        }
        return false
    }

    fun getExerciseById(id:String):Exercise{
        for(exercise in listExercises){
            if(exercise.id == id){
                return exercise
            }
        }
        throw Exception("exercise not found")
    }

    fun getNextExercise(exercise: Exercise): Exercise? {
        val index = listExercises.indexOf(exercise)
        return if (index == -1 || index + 1 == listExercises.size) {
            null
        } else {
            listExercises[index + 1]
        }

    }
}