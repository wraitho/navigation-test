package br.pedroso.navigationtest.editor

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.pedroso.navigationtest.entities.Item
import br.pedroso.navigationtest.sharedElement.SharedElementViewModel
import kotlinx.android.synthetic.main.view_my_item_bottom_sheet.view.idTextView
import kotlinx.android.synthetic.main.view_my_item_screen.view.*

class MyItemViewHolder(itemView: View, private val viewModel: SharedElementViewModel) :
    RecyclerView.ViewHolder(itemView) {
    fun bind(item: Item) = with(itemView) {
        idTextView.text = "${item.id}"

        descriptionTextView.text = item.description

        removeItemButton.setOnClickListener {
            viewModel.removeItem(item)
        }
    }
}