package com.tereshkov.walkways.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import com.tereshkov.walkways.R
import kotlinx.android.synthetic.main.fragment_rectangle.*


class RectangleFragment : Fragment() {

    private var dX: Float = 0f
    private var dY: Float = 0f
    private var newX = 0f
    private var newY = 0f

    private lateinit var rootView: View
    private lateinit var gestureDetector: GestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gestureDetector = GestureDetector(context, SwipeListener())
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_rectangle, container, false)
        return rootView
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView.setOnTouchListener { _, motionEvent ->
            gestureDetector.onTouchEvent(motionEvent)
            true
        }
        val scaleGestureDetector = ScaleGestureDetector(context, object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                val factor = detector.scaleFactor
                val product = moveTextView.animate()
                        .scaleX(factor)
                        .scaleY(factor)
                        .setDuration(0)
                        .start()
                return true
            }
        })
        moveTextView.setOnTouchListener { view, event ->
            //if (scaleGestureDetector.onTouchEvent(event)) true

            if (event.pointerCount == 1) {
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        dX = view.x - event.rawX
                        dY = view.y - event.rawY
                        true
                    }
//                MotionEvent.ACTION_MOVE -> {
//                    newX = event.rawX + dX
//                    newY = event.rawY + dY
//                    view.animate()
//                            .x(if (newX > rootView.left && newX + view.width < rootView.right) newX else view.x)
//                            .y(if (newY > rootView.top && newY + view.height < rootView.bottom) newY else view.y)
//                            .setDuration(0)
//                            .start()
//                }
                    MotionEvent.ACTION_MOVE -> {
                        newX = event.rawX + dX
                        newY = event.rawY + dY
                        view.animate()
                                .x(newX)
                                .y(newY)
                                .setDuration(0)
                                .start()
                        true
                    }
                    else -> false
                }
            } else false
        }
    }

    inner class SwipeListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            val diffY = e2.y - e1.y
            val diffX = e2.x - e1.x

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
//                        moveTextView.setTextColor(Color.parseColor("#FFE4571F"))
                    } else {
//                        moveTextView.setTextColor(Color.parseColor("#FF188469"))
                    }
                    return true
                }
            }

            return false
        }
    }

    companion object {
        private const val SWIPE_THRESHOLD = 100
        private const val SWIPE_VELOCITY_THRESHOLD = 100

        fun newInstance() = RectangleFragment()
    }
}