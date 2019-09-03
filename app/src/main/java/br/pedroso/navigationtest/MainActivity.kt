package br.pedroso.navigationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shared_element.*

class MainActivity : AppCompatActivity() {

    private val navigationController by lazy {
        Navigation.findNavController(this, R.id.navigationRootFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigationView()
        setupOpenEditorButton()
        addOnChangedDestinationListener()
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.setupWithNavController(navigationController)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            onNavDestinationSelected(item, navigationController)
//        }
    }

    private fun addOnChangedDestinationListener() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.editorFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    sharedElementRoot.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            android.R.color.holo_orange_dark
                        )
                    )
                    openEditorButton.text = getText(R.string.close_editor)
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    sharedElementRoot.setBackgroundColor(
                        ContextCompat.getColor(
                            this,
                            android.R.color.darker_gray
                        )
                    )
                    openEditorButton.text = getText(R.string.open_editor)
                }
            }
        }
    }

    private fun setupOpenEditorButton() {
        openEditorButton.setOnClickListener {
            if (navigationController.currentDestination?.id != R.id.editorFragment) {
                navigationController.navigate(R.id.action_global_open_editor)
            } else {
                navigationController.popBackStack()
            }
        }
    }
}
