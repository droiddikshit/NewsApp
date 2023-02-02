package com.akshaya.newsapp.data.local.json

import android.content.Context
import com.akshaya.newsapp.data.model.Country
import com.akshaya.newsapp.di.ApplicationContext
import com.akshaya.newsapp.utils.AppConstants
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class JsonService@Inject constructor(@ApplicationContext private val context: Context) {

    fun getCountries(): List<Country> {
        val countries = arrayListOf<Country>()
        try {
            val obj = JSONObject(loadJSONFromAssets("countries.json"))
            val status: String = obj.getString(AppConstants.STATUS)
            if (status == AppConstants.SUCCESS) {
                val contactArray: JSONArray = obj.getJSONArray("data")
                for (i in 0 until contactArray.length()) {
                    val contacts = contactArray.getJSONObject(i)
                    val name = contacts.getString(AppConstants.NAME)
                    countries.add(Country(id = "", name= name))
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return countries
    }

    private fun loadJSONFromAssets(fileName: String): String? {
        val json: String = try {
            val `is`: InputStream = context.assets.open(fileName)
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            return null
        }
        return json
    }

}