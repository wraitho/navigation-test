package br.pedroso.navigationtest.entities

import android.os.Parcelable
import androidx.recyclerview.widget.DiffUtil
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val id: Int,
    val title: String,
    val description: String
) : Parcelable

object ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(
        oldItem: Item,
        newItem: Item
    ) = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Item,
        newItem: Item
    ) = oldItem.id == newItem.id
}