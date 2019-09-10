package br.pedroso.navigationtest.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.screen.ScreenViewModel
import br.pedroso.navigationtest.screen.setupSharedElementBehaviour
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private val screenViewModel by lazy {
        ViewModelProviders.of(requireActivity())[ScreenViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSharedElementBehaviour(dummyView, screenViewModel)

        toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }
}