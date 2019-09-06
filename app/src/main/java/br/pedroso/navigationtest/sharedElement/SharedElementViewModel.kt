package br.pedroso.navigationtest.sharedElement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedElementViewModel : ViewModel() {
    private val _visibilityState = MutableLiveData<SharedElementVisibilityState>().apply {
        value = Visible
    }
    val visibilityState: LiveData<SharedElementVisibilityState>
        get() = _visibilityState

    fun hideSharedElement() {
        _visibilityState.value = Hidden
    }

    fun displaySharedElement() {
        _visibilityState.value = Visible
    }
}
