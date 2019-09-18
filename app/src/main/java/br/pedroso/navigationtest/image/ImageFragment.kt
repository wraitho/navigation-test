package br.pedroso.navigationtest.image

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.BaseFragment
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.screen.FullScreenMode
import br.pedroso.navigationtest.screen.ScreenViewModel
import br.pedroso.navigationtest.sharedElement.SharedElementViewModel
import kotlinx.android.synthetic.main.fragment_image.*


class ImageFragment : BaseFragment(R.layout.fragment_image) {

    private val screenViewModel by lazy {
        ViewModelProviders.of(requireActivity())[ScreenViewModel::class.java]
    }

    private val sharedElementViewModel by lazy {
        ViewModelProviders.of(requireActivity())[SharedElementViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sharedElementViewModel.hideSharedElement()
        screenViewModel.run {
            setFullScreenMode(FullScreenMode.ContentUnderSystemBars)
            hideBottomBar()
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        sharedElementViewModel.displaySharedElement()
        screenViewModel.run {
            setFullScreenMode(FullScreenMode.Disabled)
            displayBottomBar()
        }
    }
}