package com.v.nevi.p.sv.android.reaction.utils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

fun Drawable.overrideColor(context: Context, colorId: Int?) {
    if(colorId==null || colorId==0) return
    val colorInt = ContextCompat.getColor(context, colorId)
    when (this) {
        is GradientDrawable -> setColor(colorInt)
        is ShapeDrawable -> paint.color = colorInt
        is ColorDrawable -> color = colorInt
    }
}
fun <T> Fragment.setResult(key:String, result: T){
    findNavController().previousBackStackEntry?.savedStateHandle?.set(key,result)
}

fun <T> Fragment.getResult(key: String, resultHandler:(T)->Unit) {
    val  liveData = findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<T>(key)
    liveData?.observe(viewLifecycleOwner){
        if(it!=null){
            resultHandler.invoke(it)
            liveData.value = null
        }
    }
}