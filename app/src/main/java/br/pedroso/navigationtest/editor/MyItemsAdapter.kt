package br.pedroso.navigationtest.editor

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.entities.Item
import br.pedroso.navigationtest.entities.ItemDiffCallback
import br.pedroso.navigationtest.sharedElement.SharedElementViewModel

class MyItemsAdapter(private val viewModel: SharedElementViewModel) :
    ListAdapter<Item, MyItemViewHolder>(ItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater.inflate(R.layout.view_my_item_screen, parent, false)

        return MyItemViewHolder(view, viewModel)
    }

    override fun onBindViewHolder(holder: MyItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}