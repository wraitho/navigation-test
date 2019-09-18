package br.pedroso.navigationtest.items

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.entities.Item
import br.pedroso.navigationtest.entities.ItemDiffCallback

class ItemsAdapter : ListAdapter<Item, ItemViewHolder>(ItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.view_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}