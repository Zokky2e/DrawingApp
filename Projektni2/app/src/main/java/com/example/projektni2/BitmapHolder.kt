package com.example.projektni2

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

class BitmapHolder(var savedBitmap : Bitmap){
    fun BitMapToString(bmp : Bitmap?): String {
        val baos = ByteArrayOutputStream()
        bmp?.compress(Bitmap.CompressFormat.PNG, 100, baos)
        val b = baos.toByteArray()
        return Base64.encodeToString(b, Base64.DEFAULT)
    }
}