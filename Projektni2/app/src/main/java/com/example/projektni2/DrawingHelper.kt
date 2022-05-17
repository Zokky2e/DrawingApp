package com.example.projektni2

import android.graphics.Bitmap
import java.text.SimpleDateFormat
import java.util.*

class DrawingHelper(bmp: Bitmap) {
    private val sdf : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss")
    var dateTime : String = sdf.format(Date())

    private val holder = BitmapHolder(bmp)

    var savedBitmap: String = holder.BitMapToString(bmp)


}