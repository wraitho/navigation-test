package br.pedroso.navigationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import br.pedroso.navigationtest.extensions.*
import br.pedroso.navigationtest.screen.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shared_element.*

class MainActivity : AppCompatActivity() {

    private val navigationController by lazy {
        Navigation.findNavController(this, R.id.navigationRootFragment)
    }

    private val sharedElementViewModel by lazy {
        ViewModelProviders.of(this)[ScreenViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigationView()
        setupOpenEditorButton()
        addOnChangedDestinationListener()

        setupSharedElementVisibilityStateObserver()
        setupBottomBarVisibilityStateObserver()
        setupFullScreenModeStateObserver()
    }

    private fun setupFullScreenModeStateObserver() {
        sharedElementViewModel.fullScreenModeState.observe(this, Observer { state ->
            when(state){
                FullScreenMode.Disabled -> resetFullscreenSettings()
                FullScreenMode.ContentUnderSystemBars -> enableContentUnderSystemBars()
                FullScreenMode.LeanBack -> enableFullscreenLeanback()
                FullScreenMode.Immersive -> enableFullscreenImmersive()
                FullScreenMode.ImmersiveSticky -> enableFullscreenStickyImmersive()
            }
        })
    }

    private fun setupSharedElementVisibilityStateObserver() {
        sharedElementViewModel.sharedElementVisibility.observe(
            this,
            Observer { visibilityState ->
                when (visibilityState) {
                    SharedElementVisibility.Visible -> {
                        sharedElementRoot.visibility = View.VISIBLE
                    }
                    SharedElementVisibility.Hidden -> {
                        sharedElementRoot.visibility = View.GONE
                    }
                }
            })
    }

    private fun setupBottomBarVisibilityStateObserver() {
        sharedElementViewModel.bottomBarVisibilityState.observe(
            this,
            Observer { visibilityState ->
                when (visibilityState) {
                    BottomBarVisibilityState.Visible -> {
                        bottomNavigationView.visibility = View.VISIBLE
                    }
                    BottomBarVisibilityState.Hidden -> {
                        bottomNavigationView.visibility = View.GONE
                    }
                }
            })
    }

    private fun setupBottomNavigationView() {
        bottomNavigationView.setupWithNavController(navigationController)
//        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
//            onNavDestinationSelected(item, navigationController)
//        }
    }

    private fun addOnChangedDestinationListener() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.hideKeyboard()
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
