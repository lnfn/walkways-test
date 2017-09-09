package com.tereshkov.walkways.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View


class DrawView : View {

    private val paint = Paint()
    private val textBounds = Rect()
    private var mScaleFactor = 1f
    private var mScaleDetector: ScaleGestureDetector
    private val text = "This is tex a Good text"

    init {
        paint.color = Color.BLACK
        paint.textSize = 60f
    }

    constructor(context: Context) : super(context) {
        mScaleDetector = ScaleGestureDetector(context, ScaleListener())
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {
        mScaleDetector = ScaleGestureDetector(context, ScaleListener())
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        mScaleDetector = ScaleGestureDetector(context, ScaleListener())
    }

    override fun onDraw(canvas: Canvas) {
        canvas.scale(mScaleFactor, mScaleFactor)
        canvas.drawText(text, 50f, 50f, paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        mScaleDetector.onTouchEvent(event)
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            mScaleFactor *= detector.scaleFactor
            mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 10.0f))
            invalidate()
            return true
        }
    }
}