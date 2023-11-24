package com.example.contactlesswppchat.utils
import android.content.Context
import com.example.contactlesswppchat.CountryDataClass
import org.json.JSONArray

fun convertJsonToList(ctx: Context, fileName:String): List<CountryDataClass> {
    val jsonString = ctx.assets.open(fileName).bufferedReader().use { it.readText() }
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