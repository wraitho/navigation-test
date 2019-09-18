package br.pedroso.navigationtest.details

import android.os.Bundle
import android.view.View
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.navArgs
import br.pedroso.navigationtest.BaseFragment
import br.pedroso.navigationtest.NavigationTestApplication
import br.pedroso.navigationtest.R
import br.pedroso.navigationtest.entities.Item
import br.pedroso.navigationtest.items.ItemsFragmentDirections
import br.pedroso.navigationtest.sharedElement.SharedElementViewModel
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*

class DetailsFragment : BaseFragment(R.layout.fragment_details) {

    private val sharedElementViewModel by lazy {
        ViewModelProviders.of(requireActivity())[SharedElementViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailsFragmentArgs by navArgs()


        args.item?.run {
            view.titleTextView.text = title
            view.descriptionTextView.text = description
            setupTestNotificationButton(this)
        }
    }

    private fun setupTestNotificationButton(item: Item) {
        testNotificationButton.setOnClickListener {
            sendNotificationAboutItem(item)
        }

        addToMyItemsButton.setOnClickListener {
            sharedElementViewModel.addItem(item)
        }

        removeFromMyItemsButton.setOnClickListener {
            sharedElementViewModel.removeItem(item)
        }
    }

    private fun sendNotificationAboutItem(item: Item) {
        val args = ItemsFragmentDirections.actionDisplayItemDetails(item).arguments

        val pendingIntent = NavDeepLinkBuilder(requireContext())
            .setGraph(R.navigation.nav_graph_main)
            .setDestination(R.id.detailsFragment)
            .setArguments(args)
            .createPendingIntent()

        val builder =
            NotificationCompat.Builder(requireContext(), NavigationTestApplication.CHANNEL_ID)
                .setContentTitle(item.title)
                .setContentText(item.description)
                .setSmallIcon(R.drawable.ic_info)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(requireContext())) {
            notify(DETAILS_NOTIFICATION_ID, builder.build())
        }
    }

    companion object {
        private const val DETAILS_NOTIFICATION_ID = 123456789
    }
}