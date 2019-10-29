package br.pedroso.navigationtest.extensions

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference

infix fun BottomNavigationView.setupWith(navController: NavController) {
    val weakReference = WeakReference<BottomNavigationView>(this)
    navController.addOnDestinationChangedListener(
        object : NavController.OnDestinationChangedListener {
            override fun onDestinationChanged(
                controller: NavController,
                destination: NavDestination,
                arguments: Bundle?
            ) {
                val view = weakReference.get()
                if (view == null) {
                    navController.removeOnDestinationChangedListener(this)
                    return
                }
                val menu = view.menu

                for (itemPosition in 0 until menu.size()) {
                    val item = menu.getItem(itemPosition)
                    if (matchDestination(destination, item.itemId)) {
                        item.isChecked = true
                    }
                }
            }
        })
}

private fun matchDestination(destination: NavDestination, @IdRes destId: Int): Boolean {
    var currentDestination: NavDestination = destination

    while (currentDestination.id != destId) {
        val parent = currentDestination.parent

        if (parent != null) {
            currentDestination = parent
        } else {
            break
        }
    }
    return currentDestination.id == destId
}
