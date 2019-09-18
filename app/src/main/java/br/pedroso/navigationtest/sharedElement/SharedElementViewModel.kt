package br.pedroso.navigationtest.sharedElement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.pedroso.navigationtest.entities.Item

class SharedElementViewModel : ViewModel() {

    private val items = mutableSetOf<Item>()

    private val _itemsSet =
        MutableLiveData<Set<Item>>().apply {
            value = items
        }
    val itemsSet: LiveData<Set<Item>>
        get() = _itemsSet

    private val _sharedElementState =
        MutableLiveData<SharedElementState>().apply {
            value = SharedElementState.Empty
        }
    val sharedElementState: LiveData<SharedElementState>
        get() = _sharedElementState

    fun hideSharedElement() {
        _sharedElementState.value = SharedElementState.Hidden
    }

    fun displaySharedElement() = updateSharedElementState()

    private fun updateSharedElementState() {
        _sharedElementState.value = if (items.isEmpty()) {
            SharedElementState.Empty
        } else {
            SharedElementState.HasItems(items.toSet())
        }
    }

    private fun isMyItemsFull() = items.size >= 3

    fun addItem(item: Item): Boolean {
        var itemWasAdded = false

        if (!isMyItemsFull()) {
            if (!items.contains(item)) {
                items.add(item)

                if (_sharedElementState.value != SharedElementState.Hidden) {
                    updateSharedElementState()
                }
                _itemsSet.value = items.toSet()

                itemWasAdded = true
            }
        }

        return itemWasAdded
    }

    fun removeItem(item: Item): Boolean {
        var itemWasRemoved = false

        if (items.contains(item)) {
            items.remove(item)

            if (_sharedElementState.value != SharedElementState.Hidden) {
                updateSharedElementState()
            }

            _itemsSet.value = items.toSet()

            itemWasRemoved = true
        }

        return itemWasRemoved
    }
}