package com.v.nevi.p.sv.android.reaction.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import com.v.nevi.p.sv.android.reaction.utils.GenerateRandomNumber
import com.v.nevi.p.sv.android.reaction.R
import kotlin.properties.Delegates

private const val RADIUS = 80f
private const val SPEED = 5f

class BallView(context: Context,
               attributesSet: AttributeSet?,
               defStyleAttr: Int):androidx.appcompat.widget.AppCompatImageView(context,attributesSet,defStyleAttr) {

    constructor(context: Context) :this(context,null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0)

    var isTouchedBall = false
    var isMoving:Boolean = false
    set(value) {
        field = value
        if(value){
            generateCoordinate()
            selectDirections()
        }else{
            isTouchedBall=false
        }
        invalidate()
    }

    private val p: Paint = Paint()
    private var ballX by Delegates.notNull<Float>()
    private var ballY by Delegates.notNull<Float>()

    private val directions = mutableListOf(arrayOf(-1,1), arrayOf(-1,-1), arrayOf(1,1), arrayOf(1,-1))

    private var currentXDirection = -1
    private var currentYDirection = 1

    init {
        p.color = context.resources.getColor(R.color.red_500)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        if (isMoving) {
            canvas?.drawCircle(ballX, ballY, RADIUS, p)
            move()
            invalidate()
        }
    }

    private fun move(){
        moveByX()
        moveByY()
    }

    private fun moveByX(){
        if(currentXDirection==1) {
            if (ballX + SPEED * 1+ RADIUS >= width) {
                ballX+=currentXDirection * SPEED
                currentXDirection = -1
                return
            }
        }else {
            if (ballX + SPEED * -1 - RADIUS <= 0) {
                ballX+=currentXDirection * SPEED
                currentXDirection = 1
                return
            }
        }
        ballX+=currentXDirection * SPEED
    }

    private fun moveByY(){
        if(currentYDirection==1) {
            if (ballY + SPEED * 1+ RADIUS >= height) {
                ballY+=currentYDirection * SPEED
                currentYDirection = -1
                return
            }
        }else {
            if (ballY + SPEED * -1- RADIUS <= 0) {
                ballY+=currentYDirection * SPEED
                currentYDirection = 1
                return
            }
        }
        ballY+=currentYDirection * SPEED
    }

    private fun generateCoordinate(){
        ballX = GenerateRandomNumber.execute(RADIUS.toInt(),width - RADIUS.toInt()).toFloat()
        ballY = GenerateRandomNumber.execute(RADIUS.toInt(),height - RADIUS.toInt()).toFloat()
    }

    private fun selectDirections(){
        val index = GenerateRandomNumber.execute(0,directions.size-1)
        val arr = directions[index]
        currentXDirection = arr[0]
        currentYDirection = arr[1]
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if(isMoving) {
            isMoving = false
            val touchX = event.x
            val touchY = event.y
            if ((ballX - RADIUS <= touchX && touchX <= ballX + RADIUS) && (ballY - RADIUS <= touchY && touchY <= ballY + RADIUS)) {
                isTouchedBall=true
            }
        }
        return super.onTouchEvent(event)
    }
}