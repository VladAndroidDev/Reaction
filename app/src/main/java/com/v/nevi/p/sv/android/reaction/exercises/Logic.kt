package com.v.nevi.p.sv.android.reaction.exercises

import android.os.Parcelable
import android.view.View

abstract class Logic:IBindable {

    abstract fun createData(onDataCreatedCallback:()->Unit)

    abstract fun isAnswerCorrect(v:View):Boolean

    protected abstract val dataLogic:Data

    abstract fun create()
}