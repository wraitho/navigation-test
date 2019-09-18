package br.pedroso.navigationtest.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import com.google.android.material.animation.AnimationUtils

fun View.slideDownAndHide() {
    clearAnimation()

    animate()
        .translationY(height.toFloat())
        .setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR)
        .setDuration(175L)
        .setListener(object :
            AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                visibility = View.GONE
            }
        })
}

fun View.slideUpAndDisplay() {
    if (visibility != View.VISIBLE) {
        clearAnimation()

        animate()
            .translationY(0f)
            .setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR)
            .setDuration(225L)
            .setListener(object :
                AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    visibility = View.VISIBLE
                }
            })
    }
}

