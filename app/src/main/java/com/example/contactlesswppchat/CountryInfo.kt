package com.example.contactlesswppchat

import android.provider.ContactsContract.CommonDataKinds.Phone

data class CountryInfo(
    val name:String,
    val flag:String,
    val code:String,
    val dialCode:Phone

)
