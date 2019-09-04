package br.pedroso.navigationtest.items

import android.view.View
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.details.DetailsFragment
import br.pedroso.navigationtest.details.DetailsFragmentArgs
import br.pedroso.navigationtest.entities.Item
import kotlinx.android.synthetic.main.view_item.view.*

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(item: Item) {
        itemView.apply {
            titleTextView.text = item.title
            descriptionTextView.text = item.description

            setOnClickListener {
                val action = ItemsFragmentDirections.actionDisplayItemDetails(item)
                findNavController().navigate(action)
            }
        }
    }
}