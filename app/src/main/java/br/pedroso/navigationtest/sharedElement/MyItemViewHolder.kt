package br.pedroso.navigationtest.sharedElement

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.pedroso.navigationtest.entities.Item
import kotlinx.android.synthetic.main.view_my_item_bottom_sheet.view.*

class MyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: Item) {
        itemView.idTextView.text = "${item.id}"
    }
}