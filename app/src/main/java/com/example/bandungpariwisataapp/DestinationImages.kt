package com.example.bandungpariwisataapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DestinationImages(
    val images: List<String>
) : Parcelable
