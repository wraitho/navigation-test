package br.pedroso.navigationtest.screen

sealed class SharedElementVisibility {
    object Visible : SharedElementVisibility()
    object Hidden : SharedElementVisibility()
}