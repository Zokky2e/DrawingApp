package com.example.projektni2

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import android.view.MotionEvent
import android.view.ViewConfiguration
import android.view.ViewGroup
import com.example.projektni2.MainActivity.Companion.paintBrush
import kotlin.math.abs
import com.example.projektni2.MainActivity.Companion.currentPaths
import android.graphics.Bitmap
import com.example.projektni2.MainActivity.Companion.bitmapHolder
import com.example.projektni2.MainActivity.Companion.lastWidth


class MyCanvasView : View {

    private var params : ViewGroup.LayoutParams? = null



    companion object{
        var extraCanvas: Canvas = Canvas(bitmapHolder.savedBitmap)

        var currentBrush = paintBrush.color
        var currentStrokeWidth = lastWidth
    }

    constructor(context: Context) : super(context){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0){
        init()
    }
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr){
        init()
    }

    private fun init(){
        params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)

    }


    lateinit var bmp:Bitmap
    private var path = Path()
    private var motionTouchEventX = 0f
    private var motionTouchEventY = 0f
    private var currentX = 0f
    private var currentY = 0f
    private val touchTolerance = ViewConfiguration.get(context).scaledTouchSlop
    private val backgroundColor = ResourcesCompat.getColor(resources, R.color.colorBackground, null)
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        bmp = bitmapHolder.savedBitmap
        extraCanvas.drawColor(backgroundColor)
        extraCanvas.setBitmap(bmp)

    }


    fun setStrokeWidth(sw: Float) {
        currentStrokeWidth = sw
        invalidate()
    }
    fun setColor (brush: Int){
        currentBrush = brush
        invalidate()
    }



    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(backgroundColor)
        canvas.save()
        canvas.drawBitmap(bmp, 0f, 0f, paintBrush)
        canvas.restore()

    }
    private fun touchStart() {
        val currentPaintBrush = PaintBrush(currentBrush,currentStrokeWidth, path)
        currentPaths.add(currentPaintBrush)
        path.reset()
        path.moveTo(motionTouchEventX, motionTouchEventY)
        currentX = motionTouchEventX
        currentY = motionTouchEventY

    }


    private fun touchMove(paths: PaintBrush) {
        val dx = abs(motionTouchEventX - currentX)
        val dy = abs(motionTouchEventY - currentY)
        if (dx >= touchTolerance || dy >= touchTolerance) {
            path.quadTo(currentX, currentY, (motionTouchEventX + currentX) / 2, (motionTouchEventY + currentY) / 2)
            currentX = motionTouchEventX
            currentY = motionTouchEventY
            extraCanvas.drawPath(paths.myPath, paths.paint)
        }
        invalidate()
    }

    private fun touchUp() {
        path.reset()
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionTouchEventX = event.x
        motionTouchEventY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> touchStart()
            MotionEvent.ACTION_MOVE -> touchMove(currentPaths[currentPaths.size-1])
            MotionEvent.ACTION_UP -> touchUp()
        }
        return true
    }




}