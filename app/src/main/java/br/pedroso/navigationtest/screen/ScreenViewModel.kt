package br.pedroso.navigationtest.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScreenViewModel : ViewModel() {

    private val _bottomBarVisibilityState =
        MutableLiveData<BottomBarVisibilityState>().apply {
            value = BottomBarVisibilityState.Visible
        }
    val bottomBarVisibilityState: LiveData<BottomBarVisibilityState>
        get() = _bottomBarVisibilityState

    private val _fullScreenModeState =
        MutableLiveData<FullScreenMode>().apply {
            value = FullScreenMode.Disabled
        }

    val fullScreenModeState: LiveData<FullScreenMode>
        get() = _fullScreenModeState

    fun setFullScreenMode(fullScreenMode: FullScreenMode) {
        _fullScreenModeState.value = fullScreenMode
    }

    fun hideBottomBar() {
        _bottomBarVisibilityState.value = BottomBarVisibilityState.Hidden
    }

    fun displayBottomBar() {
        _bottomBarVisibilityState.value = BottomBarVisibilityState.Visible
    }

}
