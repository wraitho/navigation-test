package br.pedroso.navigationtest.bookmarks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.searchToolbar.setupSearchQueryEditText
import kotlinx.android.synthetic.main.fragment_bookmarks.*
import kotlinx.android.synthetic.main.view_search_toolbar.*

class BookmarksFragment : Fragment() {

    private val navigationController by lazy {
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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

        setupSearchQueryEditText(
            findNavController(),
            resources,
            searchQueryEditText,
            profileImageView
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