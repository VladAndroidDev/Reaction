package com.v.nevi.p.sv.android.reaction.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import com.v.nevi.p.sv.android.reaction.R

private const val RADIUS = 20f
class CircleView(context: Context,
                      attributesSet: AttributeSet?,
                      defStyleAttr: Int):androidx.appcompat.widget.AppCompatImageView(context,attributesSet,defStyleAttr) {

    constructor(context: Context) :this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    private val p:Paint = Paint()
    private val centerX:Float
    private val centerY:Float

    init {
        p.color = context.resources.getColor(R.color.red_500)
        centerX = (width/2).toFloat()
        centerY = (height/2).toFloat()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centerX,centerY, RADIUS,p)
    }

}