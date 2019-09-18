package br.pedroso.navigationtest.editor

import android.os.Build
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.BaseFragment
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.sharedElement.SharedElementViewModel
import kotlinx.android.synthetic.main.fragment_editor.*

class EditorFragment : BaseFragment(R.layout.fragment_editor) {
    private val sharedElementViewModel by lazy {
        ViewModelProviders.of(requireActivity())[SharedElementViewModel::class.java]
    }

    private val adapter by lazy {
        MyItemsAdapter(sharedElementViewModel)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sharedElementEnterTransition =
                TransitionInflater.from(context).inflateTransition(android.R.transition.explode)
        }
        sharedElementViewModel.hideSharedElement()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        setupItems()
    }

    private fun setupItems() {
        myItemsRecyclerView.adapter = adapter

        sharedElementViewModel.itemsSet.observe(this, Observer { items ->
            adapter.submitList(items.toList())
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sharedElementViewModel.displaySharedElement()
    }

}