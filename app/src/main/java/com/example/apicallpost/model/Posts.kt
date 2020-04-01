package com.example.apicallpost.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Posts(
    var id : Int,
    var userID : Int,
    var title : String,
    var body : String
) : Parcelable
