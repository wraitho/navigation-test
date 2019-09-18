package br.pedroso.navigationtest.sharedElement

import android.animation.LayoutTransition
import android.content.res.Resources
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import br.pedroso.navigationtest.R
import kotlinx.android.synthetic.main.view_shared_element.view.*

fun setupSharedElement(
    lifecycleOwner: LifecycleOwner,
    sharedElementRoot: View,
    sharedElementViewModel: SharedElementViewModel,
    navController: NavController,
    resources: Resources
    ) {
    val myItemsRecyclerView = sharedElementRoot.myItemsRecyclerView

    (sharedElementRoot as? ViewGroup)?.layoutTransition?.enableTransitionType(LayoutTransition.CHANGING)

    val adapter = MyItemsAdapter()

    myItemsRecyclerView.adapter = adapter

    sharedElementRoot.setOnClickListener {
        if(navController.currentDestination?.id != R.id.editorFragment) {
            val extras = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                FragmentNavigatorExtras(
                    sharedElementRoot to resources.getString(R.string.transition_shared_element)
                )
            } else {
                null
            }

            navController.navigate(
                R.id.action_global_open_editor,
                null,
                null,
                extras
            )
        }
    }

    sharedElementViewModel.sharedElementState.observe(
        lifecycleOwner,
        Observer { state ->
            when (state) {
                is SharedElementState.HasItems -> {
                    sharedElementRoot.visibility = View.VISIBLE
                    myItemsRecyclerView.visibility = View.VISIBLE
                    adapter.submitList(state.items.toList())
                }
                SharedElementState.Empty -> {
                    myItemsRecyclerView.visibility = View.GONE
                    sharedElementRoot.visibility = View.VISIBLE
                }
                SharedElementState.Hidden -> {
                    sharedElementRoot.visibility = View.GONE
                }
            }
        })
}
