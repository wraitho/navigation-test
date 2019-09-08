package br.pedroso.navigationtest.screen

sealed class BottomBarVisibilityState {
    object Visible : BottomBarVisibilityState()
    object Hidden : BottomBarVisibilityState()
}