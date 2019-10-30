package br.pedroso.navigationtest.searchToolbar

import android.content.res.Resources
import android.os.Build
import android.widget.EditText
import android.widget.ImageView
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import br.pedroso.navigationtest.R

/**
 * This is the action that (opens the profile) has to be executed by the host. As the Host can be different fragments
 * like Items, Bookmarks and Settings in this case, instead of creating interface
 * we can pass a function as an argument to the lambda that shall be executed if the ViewModels
 * are at that point...
 */
typealias ProfileNavigation = (() -> Unit) -> Unit

/**
 * Similar to ProfileNavigation, this alias is for making the lambda-in-lambda more readable.
 */
typealias SearchNavigation = (() -> Unit) -> Unit

fun setupSearchQueryEditText(
    navController: NavController,
    resources: Resources,
    searchQueryEditText: EditText,
    profileImageView: ImageView,
    searchNavigation: SearchNavigation,
    profileNavigation: ProfileNavigation
) {
    searchQueryEditText.setOnClickListener {
        val extras = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            FragmentNavigatorExtras(
                it to resources.getString(R.string.transition_search_text)
            )
        } else {
            null
        }

        searchNavigation {
            navController.navigate(
                R.id.action_global_open_search,
                null,
                null,
                extras
            )
        }
    }


    profileImageView.setOnClickListener {
        profileNavigation {
            navController.navigate(R.id.action_global_open_profile, null, null)
        }
    }
}