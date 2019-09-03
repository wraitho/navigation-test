package br.pedroso.navigationtest.sharedToolbar

import android.content.res.Resources
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import br.pedroso.navigationtest.R

class SetupSharedToolbarDelegate {

    companion object {
        fun setupSearchQueryEditText(
            navController: NavController,
            resources: Resources,
            searchQueryEditText: EditText
        ) {
            searchQueryEditText.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    it to resources.getString(R.string.transition_search_text)
                )

                navController.navigate(
                    R.id.action_global_open_search,
                    null,
                    null,
                    extras
                )
            }
        }
    }

}