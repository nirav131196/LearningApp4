package com.example.loginapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.widget.TextView
import com.google.android.material.slider.Slider

class RangeSlider : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_range_slider)



    }
    fun Slider.showLabelForever()
    {
        // start showing the label immediately
        showLabelUntilNextTouchUp()

        // send a touch event when user stops touching the slider to trigger the
        // label to show up again
        addOnSliderTouchListener(
            object:Slider.OnSliderTouchListener
            {
                override fun onStartTrackingTouch(slider:Slider) = Unit
                override fun onStopTrackingTouch(slider:Slider) = showLabelUntilNextTouchUp()
            }
        )
    }

    fun Slider.showLabelUntilNextTouchUp()
    {
        // sent a touch event to cause the label for the slider to show.
        // a side effect of calling onTouchEvent is that it will cause
        // the slider to change the progress value; we save the original
        // progress value before calling onTouchEvent, then restore the
        // value after calling onTouchEvent.
        val originalValue = value
        onTouchEvent(
            MotionEvent.obtain(0L,0L,MotionEvent.ACTION_DOWN,0f,0f,0)
        )
        value = originalValue
    }
}