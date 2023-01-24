package com.akshaya.newsapp.utils

import android.content.Context
import java.io.InputStream
import java.nio.charset.Charset

object ReadJsonHelper {

     fun loadJSONFromAssets(context: Context): String? {
        val json: String
        json = try {
            val `is`: InputStream = context.assets.open("countries.json")
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