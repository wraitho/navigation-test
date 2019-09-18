package br.pedroso.navigationtest.sharedElement

import br.pedroso.navigationtest.entities.Item

sealed class SharedElementState {
    class HasItems(val items: Set<Item>) : SharedElementState()
    object Empty : SharedElementState()
    object Hidden : SharedElementState()
}