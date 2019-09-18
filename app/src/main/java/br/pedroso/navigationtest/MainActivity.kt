package br.pedroso.navigationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import br.pedroso.navigationtest.extensions.*
import br.pedroso.navigationtest.screen.*
import br.pedroso.navigationtest.sharedElement.SharedElementViewModel
import br.pedroso.navigationtest.sharedElement.setupSharedElement
import com.google.android.material.behavior.HideBottomViewOnScrollBehavior
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.view_shared_element.*

class MainActivity : AppCompatActivity() {

    private val navigationController by lazy {
        Navigation.findNavController(this, R.id.navigationRootFragment)
    }

    private val screenViewModel by lazy {
        ViewModelProviders.of(this)[ScreenViewModel::class.java]
    }

    private val sharedElementViewModel by lazy {
        ViewModelProviders.of(this)[SharedElementViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupBottomNavigationView()
        addOnChangedDestinationListener()

        setupSharedElement(
            this,
            sharedElementRoot,
            sharedElementViewModel,
            navigationController,
            resources
        )
        setupBottomBarVisibilityStateObserver()
        setupFullScreenModeStateObserver()
    }

    private fun setupFullScreenModeStateObserver() {
        screenViewModel.fullScreenModeState.observe(this, Observer { state ->
            when (state) {
                FullScreenMode.Disabled -> resetFullscreenSettings()
                FullScreenMode.ContentUnderSystemBars -> enableContentUnderSystemBars()
                FullScreenMode.LeanBack -> enableFullscreenLeanback()
                FullScreenMode.Immersive -> enableFullscreenImmersive()
                FullScreenMode.ImmersiveSticky -> enableFullscreenStickyImmersive()
            }
        })
    }

    private fun setupBottomBarVisibilityStateObserver() {
        screenViewModel.bottomBarVisibilityState.observe(
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
    }

    private fun addOnChangedDestinationListener() {
        navigationController.addOnDestinationChangedListener { _, destination, _ ->
            bottomNavigationView.hideKeyboard()
            screenViewModel.displayBottomBar()
        }
    }
}
