package br.pedroso.navigationtest.screen

sealed class FullScreenMode {
    object Disabled : FullScreenMode()
    object LeanBack : FullScreenMode()
    object Immersive : FullScreenMode()
    object ImmersiveSticky : FullScreenMode()
    object ContentUnderSystemBars : FullScreenMode()
}