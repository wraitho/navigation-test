package br.pedroso.navigationtest.searchToolbar

import android.content.res.Resources
import android.os.Build
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import br.pedroso.navigationtest.R

fun setupSearchQueryEditText(
    navController: NavController,
    resources: Resources,
    searchQueryEditText: EditText,
    profileImageView: ImageView
) {
    searchQueryEditText.setOnClickListener {
        val extras = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            FragmentNavigatorExtras(
                it to resources.getString(R.string.transition_search_text)
            )
        } else {
            null
        }

        navController.navigate(
            R.id.action_global_open_search,
            null,
            null,
            extras
        )
    }


    profileImageView.setOnClickListener {
        navController.navigate(R.id.action_global_open_profile, null, null)
    }
}