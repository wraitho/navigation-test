package br.pedroso.navigationtest.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.sharedToolbar.SetupSharedToolbarDelegate
import kotlinx.android.synthetic.main.view_search_toolbar.*

class BookmarksFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.run {
            if (containsKey(KEY_REGION_CODE)) {
                Toast.makeText(
                    requireContext(),
                    getString(KEY_REGION_CODE, REGION_NONE),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        if(arguments?.containsKey(KEY_REGION_CODE) == true) {

        }

        SetupSharedToolbarDelegate.setupSearchQueryEditText(
            findNavController(),
            resources,
            searchQueryEditText
        )
    }

    companion object {
        private const val KEY_REGION_CODE = "region_code"
        private const val REGION_NONE = "none"
    }
}