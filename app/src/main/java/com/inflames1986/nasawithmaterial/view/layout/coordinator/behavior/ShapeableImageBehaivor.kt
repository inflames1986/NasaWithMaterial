package com.inflames1986.nasawithmaterial.view.layout.coordinator.behavior

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.inflames1986.nasawithmaterial.R
import kotlin.math.abs

class ShapeableImageBehaivor(
    context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<View>(context, attrs) {


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return (dependency is AppBarLayout)
    }


    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        Log.d("", "")

        val bar = dependency as AppBarLayout
        child.alpha = 1 - abs(2 * bar.y) / bar.height.toFloat()
        child.y =
            (bar.width.toFloat() + child.width.toFloat()) * (1 - abs(3 * bar.y) / bar.height.toFloat())
        child.setBackgroundResource(R.drawable.bg_earth)
        Log.d("@@@", child.y.toString())
        return super.onDependentViewChanged(parent, child, dependency)
    }
}