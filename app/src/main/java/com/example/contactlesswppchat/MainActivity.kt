package com.example.contactlesswppchat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.contactlesswppchat.utils.CountryAdapter
import com.example.contactlesswppchat.utils.convertJsonToList



class MainActivity : ComponentActivity() {

    private lateinit var editCountryText: EditText
    private lateinit var editAreaText: EditText
    private lateinit var editPhoneText: EditText
    private lateinit var countrySpinner: Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.main_activity)

        editCountryText = findViewById(R.id.editCountryText)
        editAreaText = findViewById(R.id.editAreaText)
        editPhoneText = findViewById(R.id.editPhoneText)
        countrySpinner = findViewById(R.id.countryCodeSpinner)

        val countryDataList = convertJsonToList(this,"country_dial_info.json")
        val adapter = CountryAdapter(this,countryDataList)
        countrySpinner.adapter = adapter
        countrySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedCountry = countryDataList[position]
                editCountryText.setText(selectedCountry.dialCode)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    fun openWhatsapp(view:View){
        val countryCode = editCountryText.text.toString()
        val areaCode = editAreaText.text.toString()
        val phoneNumber = editPhoneText.text.toString()
        if (countryCode.isNotEmpty() && areaCode.isNotEmpty() && phoneNumber.isNotEmpty()) {
            // Construye el enlace de WhatsApp
            val whatsappUrl = "https://wa.me/$countryCode$areaCode$phoneNumber"

            // Abre WhatsApp
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(whatsappUrl)
            startActivity(intent)
        } else {
            // Muestra un mensaje de error si algún campo está vacío
            showToast("completa todos los campos.")
        }
    }

    // Función para mostrar mensajes temporales
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }




}




