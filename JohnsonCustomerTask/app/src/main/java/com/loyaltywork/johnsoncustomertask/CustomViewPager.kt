package com.loyaltywork.johnsoncustomertask

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.loyaltywork.johnsoncustomertask.R
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import android.view.View.MeasureSpec

class CustomViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

   // var tabLayout: TabLayout? = null
    lateinit var tabLayout: TabLayout
    var view: View? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        tabLayout = rootView.findViewById(R.id.tabLayout)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                when (tab.position) {
                    0 -> getCustomHeight(0, widthMeasureSpec, heightMeasureSpec)
                    1 -> getCustomHeight(1, widthMeasureSpec, heightMeasureSpec)
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
        getCustomHeight(0, widthMeasureSpec, heightMeasureSpec)
    }
    private fun measureHeight(measureSpec: Int, view: View?): Int {
        var result = 0
        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize
        } else {
            // set the height from the base view if available
            if (view != null) {
                result = view.measuredHeight
            }
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize)
            }
        }
        return result
    }

    // Setting code of height for wrap content.
    fun getCustomHeight(id: Int, width: Int, height: Int) {
        view = getChildAt(id)
        if (view != null) {
            view!!.measure(width, height)
        }
        setMeasuredDimension(measuredWidth, measureHeight(height, view))
    }
}