package br.pedroso.navigationtest.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    val id: Int,
    val title: String,
    val description: String
) : Parcelable