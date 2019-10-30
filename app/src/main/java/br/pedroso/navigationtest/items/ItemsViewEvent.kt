package br.pedroso.navigationtest.items

import br.pedroso.navigationtest.entities.Item

sealed class ItemsViewEvent {
    data class OnItemClicked(val item: Item, val navigationAction: () -> Unit) : ItemsViewEvent()
    data class OnProfileClicked(val navigationAction: () -> Unit) : ItemsViewEvent()
    data class OnSearchAction(val navigationAction: () -> Unit) : ItemsViewEvent()
}
