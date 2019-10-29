package br.pedroso.navigationtest.home

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.extensions.*
import br.pedroso.navigationtest.screen.BottomBarVisibilityState
import br.pedroso.navigationtest.screen.FullScreenMode
import br.pedroso.navigationtest.screen.ScreenViewModel
import br.pedroso.navigationtest.screen.SharedElementVisibility
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shared_element.*

class HomeActivity : AppCompatActivity() {

    private val navigationController by lazy {
        Navigation.findNavController(this, R.id.navigationRootFragment)
    }

    private val sharedElementViewModel by lazy {
        ViewModelProviders.of(this)[ScreenViewModel::class.java]
    }

    private val mainViewModel by lazy {
        ViewModelProviders.of(
            this,
            MainViewModel.Factory { message -> Log.d("XLog", message) })[MainViewModel::class.java]
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
            when (state) {
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
        bottomNavigationView setupWith navigationController
        bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            mainViewModel.onViewEvent(
                HomeViewEvent.BottomNavigationItemClicked(menuItem.title.toString()) {
                    val currentDestination = navigationController.currentDestination
                    if (currentDestination?.id != menuItem.itemId &&
                        currentDestination?.parent?.id != menuItem.itemId
                    ) {
                        NavigationUI.onNavDestinationSelected(menuItem, navigationController)
                    }
                })
            true
        }
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
            mainViewModel.onViewEvent(HomeViewEvent.EditorButtonClicked {
                if (navigationController.currentDestination?.id != R.id.editorFragment) {
                    navigationController.navigate(R.id.action_global_open_editor)
                } else {
                    navigationController.popBackStack()
                }
            })
        }
    }
}
