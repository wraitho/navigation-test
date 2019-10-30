package br.pedroso.navigationtest.items

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import br.pedroso.navigationtest.BaseFragment
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.entities.Item
import br.pedroso.navigationtest.screen.ScreenViewModel
import br.pedroso.navigationtest.screen.setupSharedElementBehaviour
import br.pedroso.navigationtest.searchToolbar.setupSearchQueryEditText
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.view_search_toolbar.*

class ItemsFragment : BaseFragment(R.layout.fragment_items) {

    private val screenViewModel by lazy {
        ViewModelProviders.of(requireActivity())[ScreenViewModel::class.java]
    }

    private val itemsViewModel by lazy {
        ViewModelProviders.of(
            this,
            ItemsViewModel.Factory { message ->
                Log.d(
                    "XLog",
                    message
                )
            })[ItemsViewModel::class.java]
    }

    private val itemsAdapter by lazy { ItemsAdapter(itemsViewModel) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        setupSearchQueryEditText(
            findNavController(),
            resources,
            searchQueryEditText,
            profileImageView,
            { navigateToSearch ->
                itemsViewModel.onViewEvent(ItemsViewEvent.OnSearchAction(navigateToSearch))
            },
            { navigateToProfile ->
                itemsViewModel.onViewEvent(ItemsViewEvent.OnProfileClicked(navigateToProfile))
            })

        setupSharedElementBehaviour(dummyView, screenViewModel)
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