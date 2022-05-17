package com.example.projektni2

import android.graphics.Paint
import android.graphics.Path

class PaintBrush(color: Int,strokeWidth: Float, path: Path) {

    val myPath: Path = path
    val paint = Paint().apply {
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        this.color = color
        this.strokeWidth = strokeWidth

    }
}