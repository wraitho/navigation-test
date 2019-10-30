package br.pedroso.navigationtest.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.BaseFragment
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.searchToolbar.setupSearchQueryEditText
import kotlinx.android.synthetic.main.fragment_settings.*
import kotlinx.android.synthetic.main.view_search_toolbar.*

class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchQueryEditText(
            findNavController(),
            resources,
            searchQueryEditText,
            profileImageView,
            { navigateToSearch -> navigateToSearch() },
            { navigateToProfile -> navigateToProfile() }
        )

        displayWarningButton.setOnClickListener {
            findNavController().navigate(R.id.warningFragment)
        }

        displayBottomWarningButton.setOnClickListener {
            findNavController().navigate(R.id.bottomWarningFragment)
        }
    }
}