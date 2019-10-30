package br.pedroso.navigationtest.bookmarks

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.BaseFragment
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.searchToolbar.setupSearchQueryEditText
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import kotlinx.android.synthetic.main.view_search_toolbar.*

class BookmarksFragment : BaseFragment(R.layout.fragment_bookmarks) {

    private val navigationController by lazy {
        findNavController()
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

        setupSearchQueryEditText(
            findNavController(),
            resources,
            searchQueryEditText,
            profileImageView,
            { navigateToSearch -> navigateToSearch() },
            { navigateToProfile -> navigateToProfile() }
        )

        openImageButton.setOnClickListener {
            navigationController.navigate(R.id.action_open_image)
        }
    }

    companion object {
        private const val KEY_REGION_CODE = "region_code"
        private const val REGION_NONE = "none"
    }
}