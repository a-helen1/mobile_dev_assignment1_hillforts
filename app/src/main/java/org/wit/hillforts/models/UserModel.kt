package org.wit.hillforts.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String= "") : Parcelable