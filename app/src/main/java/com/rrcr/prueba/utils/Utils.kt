package com.rrcr.prueba.utils

import android.util.Log
import android.widget.Button
import android.widget.ImageView
import com.rrcr.prueba.R
import com.rrcr.prueba.data.model.MainData
import com.squareup.picasso.Picasso
import java.security.MessageDigest
import java.util.*
import javax.inject.Inject

class Utils @Inject constructor() {
    fun getTimestampString(): String {
        return Date().time.toString()
    }

    fun getHash(toEncode: String): String {
        val md5 = MessageDigest.getInstance("MD5")
        val bytes = md5.digest(toEncode.toByteArray())
        return bytes.toHex()
    }

    private fun ByteArray.toHex(): String {
        return joinToString("") { "%02x".format(it) }
    }
}

//Extensiones
fun ImageView.load(url: String) {
    if (url.isNotEmpty()) {
        Log.d("****TAG PICASSO", url)
        Picasso.get().load(url).error(R.drawable.ic_launcher_background).into(this)
    }
}

fun Button.detailClickListener(){
    this.setOnClickListener {

    }
}