package br.pedroso.navigationtest.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.entities.Item
import br.pedroso.navigationtest.searchToolbar.setupSearchQueryEditText
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.view_search_toolbar.*

class ItemsFragment : Fragment() {

    private val itemsAdapter = ItemsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupSearchQueryEditText(findNavController(), resources, searchQueryEditText)
    }

    private fun setupRecyclerView() {
        itemsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = itemsAdapter
        }

        val items = createFakeItems()

        itemsAdapter.submitList(items)
    }

    private fun createFakeItems(amountOfItems: Int = 100): List<Item> {
        return (1..amountOfItems).map { id ->
            Item(
                id = id,
                title = "Item $id",
                description = "Description for $id"
            )
        }
    }


}