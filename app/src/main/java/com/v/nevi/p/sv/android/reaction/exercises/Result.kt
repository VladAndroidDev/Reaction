package com.v.nevi.p.sv.android.reaction.exercises

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
class Result(val time:Long,val minimumTime:Long,val numberMistakes:Int,val exerciseNameId:Int,val minimumMistakes:Int):Parcelable