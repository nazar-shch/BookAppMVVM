package com.example.l7.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Book(
    var id: Int = 0,
    var title: String = "",
    var author: String = "",
    var year: Int = 0

) : Parcelable