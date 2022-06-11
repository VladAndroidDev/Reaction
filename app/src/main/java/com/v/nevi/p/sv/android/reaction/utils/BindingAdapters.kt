package com.v.nevi.p.sv.android.reaction.utils

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.util.Log
import android.view.View
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.google.android.material.slider.Slider
import com.v.nevi.p.sv.android.reaction.R
import com.v.nevi.p.sv.android.reaction.exercises.OnProgressChangedSliderListener
import com.v.nevi.p.sv.android.reaction.views.BallView


@BindingAdapter(value = ["color"])
fun setColor(view: View, id: Int?) {
    if (id == null) return
    val drawable = view.background
    drawable.overrideColor(view.context, id)
}

@BindingAdapter(value = ["moving"])
fun setMoving(ballView: BallView, value: Boolean?) {
    Log.d("MyTag", "isMoving")
    if (value != null) {
        ballView.isMoving = value
    }
}

@BindingAdapter("penalty")
fun setPenalty(textView: TextView, value: Long?) {
    if (value != null) {
        textView.text = "+${value/1000.0} "+textView.context.resources.getString(R.string.sec)
        val animation = AnimatorInflater.loadAnimator(textView.context,R.animator.penalty_animator) as AnimatorSet
        animation.setTarget(textView)
        animation.start()
        //textView.visibility = View.GONE

    }
}

@BindingAdapter(value = ["text_view_label", "listener", "start_value"], requireAll = true)
fun onProgressChangedListener(
    slider: Slider,
    label: TextView,
    listener: OnProgressChangedSliderListener,
    startValue: Int
) {
    val context = label.context
    if(startValue==0){
        slider.value = slider.valueFrom
        label.text =
            context.getText(R.string.number_of_rounds).toString() + " " + slider.valueFrom.toInt().toString()
    }else {
        slider.value = startValue.toFloat()
        label.text =
            context.getText(R.string.number_of_rounds).toString() + " " + startValue.toString()
    }
    slider.addOnChangeListener{ _: Slider, _value: Float, _: Boolean ->
        label.text = context.getText(R.string.number_of_rounds).toString()+" " + _value.toInt().toString()
        listener.onProgressChangeListener(_value.toInt())
    }

}

@BindingAdapter(value = ["text_id"])
fun setText(textView: TextView,id:Int?){
    if(id==null) return
    textView.setText(id)
}
