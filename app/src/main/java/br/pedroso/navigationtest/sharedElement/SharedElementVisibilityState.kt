package br.pedroso.navigationtest.sharedElement

sealed class SharedElementVisibilityState
object Visible : SharedElementVisibilityState()
object Hidden : SharedElementVisibilityState()