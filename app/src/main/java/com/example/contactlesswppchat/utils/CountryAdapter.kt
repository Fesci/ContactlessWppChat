package com.example.contactlesswppchat.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.contactlesswppchat.CountryDataClass
import com.example.contactlesswppchat.R

class CountryAdapter(context: Context, countries: List<CountryDataClass>) :
    ArrayAdapter<CountryDataClass>(context, R.layout.custom_spinner, countries) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createCustomView(position, convertView, parent, showFullName = false)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createCustomView(position, convertView, parent, showFullName = true)
    }

    private fun createCustomView(position: Int, convertView: View?, parent: ViewGroup, showFullName: Boolean): View {
        val selectedCountry = getItem(position)

        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.custom_spinner, parent, false)

        val textViewCountryCode = view.findViewById<TextView>(R.id.textViewCountryCode)
        val textViewFullName = view.findViewById<TextView>(R.id.textViewFullName)

        if (showFullName) {
            textViewCountryCode.visibility = View.GONE
            textViewFullName.visibility = View.VISIBLE
            textViewFullName.text = "${selectedCountry?.flag} ${selectedCountry?.name} ${selectedCountry?.dialCode}"
        } else {
            textViewCountryCode.visibility = View.VISIBLE
            textViewFullName.visibility = View.GONE
            textViewCountryCode.text = "${selectedCountry?.flag} ${selectedCountry?.code} ${selectedCountry?.dialCode}"
        }

        return view
    }
}
