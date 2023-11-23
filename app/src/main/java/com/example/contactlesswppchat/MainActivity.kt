package com.example.contactlesswppchat

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.contactlesswppchat.ui.theme.ContactlessWppChatTheme


class MainActivity : ComponentActivity() {

    private lateinit var editCountryText: EditText
    private lateinit var editAreaText: EditText
    private lateinit var editPhoneText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            setContentView(R.layout.main_activity)

        editCountryText = findViewById(R.id.editCountryText)
        editAreaText = findViewById(R.id.editAreaText)
        editPhoneText = findViewById(R.id.editPhoneText)
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




