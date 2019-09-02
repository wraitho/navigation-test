package br.pedroso.navigationtest.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.R
import kotlinx.android.synthetic.main.fragment_items.*
import kotlinx.android.synthetic.main.view_search_toolbar.*

class ItemsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayDetailsButton.setOnClickListener {
            findNavController().navigate(R.id.detailsFragment)
        }

        searchQueryEditText.setOnClickListener {
            val extras = FragmentNavigatorExtras(
                it to getString(R.string.transition_search_text)
            )

            findNavController().navigate(
                R.id.action_global_open_search,
                null,
                null,
                extras
            )
        }

    }
}