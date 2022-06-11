package com.v.nevi.p.sv.android.reaction.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import com.v.nevi.p.sv.android.reaction.R
import kotlin.properties.Delegates

class ClickEffectView(context: Context,
                      attributesSet: AttributeSet?,
                      defStyleAttr: Int):androidx.appcompat.widget.AppCompatImageView(context,attributesSet,defStyleAttr) {

    constructor(context: Context) :this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    private val p:Paint = Paint()

    init {
        p.color = context.resources.getColor(R.color.grey_click)
        isClickable = true
    }

    private var isTouched = false
    private var touchX by Delegates.notNull<Float>()
    private var touchY by Delegates.notNull<Float>()
    private var timer = 0f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if(isTouched){
            if (timer >= 10f){
                isTouched = false
                invalidate()
                timer = 0f
            }
            canvas?.drawCircle(touchX,touchY,timer*12F,p)
            timer+=0.7f
            invalidate()
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        isTouched = true
        touchX = event.x
        touchY = event.y

        return super.onTouchEvent(event)
    }

}