package com.example.contactlesswppchat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.example.contactlesswppchat.assets.readJsonFile
import org.json.JSONArray
import com.example.contactlesswppchat.CountryDataClass


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

        val countryData = loadCountryData()

        val countryNames = countryData.map { it.name }
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countryNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        countrySpinner.adapter = adapter

        // Set a listener to handle item selection
        //TODO
        //countrySpinner.setOnItemSelectedListener { parent, view, position, id ->
          //  val selectedCountry = countryData[position]
            //editCountryText.text = selectedCountry.dialCode
        //}
    }
    private fun loadCountryData(): List<CountryDataClass> {
        val jsonFileName = "country_dial_info.json"  // Replace with your actual file name
        val jsonString = readJsonFile(this,jsonFileName)
        val jsonArray = JSONArray(jsonString)

        val countries = mutableListOf<CountryDataClass>()

        for (i in 0 until jsonArray.length()) {
            val jsonCountry = jsonArray.getJSONObject(i)
            val name = jsonCountry.getString("name")
            val flag = jsonCountry.getString("flag")

            val code = jsonCountry.getString("code")
            val dialCode = jsonCountry.getString("dial_code")

            countries.add(CountryDataClass(name, flag, code, dialCode))
        }

        return countries
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
            showToast("Por favor, completa todos los campos.")
        }
    }

    // Función para mostrar mensajes temporales
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }




}




