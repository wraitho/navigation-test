package br.pedroso.navigationtest.items

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.pedroso.navigationtest.typealiases.LogFunction

class ItemsViewModel(private val log: LogFunction) : ViewModel(), ItemsViewEventHandler {
    override fun onViewEvent(viewEvent: ItemsViewEvent) {
        when (viewEvent) {
            is ItemsViewEvent.OnItemClicked -> {
                log("Item Clicked: ${viewEvent.item.title}")
                viewEvent.navigationAction()
            }
            is ItemsViewEvent.OnProfileClicked -> {
                log("On Profile Clicked")
                viewEvent.navigationAction()
            }
            is ItemsViewEvent.OnSearchAction -> {
                log("SearchView clicked")
                viewEvent.navigationAction()
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    class Factory(private val log: LogFunction) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return ItemsViewModel(log) as T
        }
    }

}