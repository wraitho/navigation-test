package br.pedroso.navigationtest.extensions

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

private val Context.inputMethodManager
    get() = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

fun View.showKeyboard() = context?.run {
    inputMethodManager.toggleSoftInput(
        InputMethodManager.SHOW_FORCED,
        InputMethodManager.HIDE_IMPLICIT_ONLY
    )
}

fun View.hideKeyboard() = context?.run {
    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
}