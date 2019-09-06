package br.pedroso.navigationtest.items

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat

class NestedScrollListenerBehaviour<V : View> : CoordinatorLayout.Behavior<V> {
    constructor() : super()
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private var currentState = STATE_SCROLLED_UP

    private var listener: NestedScrollListener? = null

    override fun onStartNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        directTargetChild: View,
        target: View,
        axes: Int,
        type: Int
    ): Boolean {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL
    }

    override fun onNestedScroll(
        coordinatorLayout: CoordinatorLayout,
        child: V,
        target: View,
        dxConsumed: Int,
        dyConsumed: Int,
        dxUnconsumed: Int,
        dyUnconsumed: Int,
        type: Int
    ) {
        if (dyConsumed > 0 && currentState != STATE_SCROLLED_DOWN) {
            currentState = STATE_SCROLLED_DOWN
            listener?.onScrollDown()
        } else if (dyConsumed < 0 && currentState != STATE_SCROLLED_UP) {
            currentState = STATE_SCROLLED_UP
            listener?.onScrollUp()
        }
    }

    fun setNestedScrollListener(listener: NestedScrollListener) {
        this.listener = listener
    }

    interface NestedScrollListener {
        fun onScrollDown()
        fun onScrollUp()
    }

    companion object {
        private const val STATE_SCROLLED_UP = 2
        private const val STATE_SCROLLED_DOWN = 1
    }
}