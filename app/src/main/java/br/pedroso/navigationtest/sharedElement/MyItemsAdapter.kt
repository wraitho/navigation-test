package br.pedroso.navigationtest.sharedElement

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.entities.Item
import br.pedroso.navigationtest.entities.ItemDiffCallback

class MyItemsAdapter : ListAdapter<Item, MyItemViewHolder>(ItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.view_my_item_bottom_sheet, parent, false)

        return MyItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}