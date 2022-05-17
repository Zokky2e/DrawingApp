package com.example.projektni2

import android.graphics.Bitmap

data class Drawing(
    val dateTime: String? = "",
    val savedBitmap: Bitmap? = Bitmap.createBitmap(2400,2400,Bitmap.Config.ARGB_8888)


)
