package br.pedroso.navigationtest.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

typealias LogFunction = (message: String) -> Unit

class MainViewModel(val log: LogFunction) : ViewModel() {

    fun onViewEvent(viewEvent: HomeViewEvent) {
        when (viewEvent) {
            is HomeViewEvent.BottomNavigationItemClicked -> {
                log("Bottom Navigation item clicked: ${viewEvent.clickedItem}")
                viewEvent.navigationAction()
            }
            is HomeViewEvent.EditorButtonClicked -> {
                log("Bottom Navigation item clicked: OnEditorButtonClicked")
                viewEvent.navigationAction()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val log: LogFunction) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(log) as T
        }
    }
}
