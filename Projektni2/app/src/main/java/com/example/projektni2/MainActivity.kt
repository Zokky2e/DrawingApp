package com.example.projektni2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.projektni2.MyCanvasView.Companion.currentBrush

class MainActivity : AppCompatActivity(), OnDataPass {

    companion object{
        var paintBrush = Paint()
        var currentPaths: ArrayList<PaintBrush> = ArrayList<PaintBrush>()
        var bitmapHolder : BitmapHolder = BitmapHolder(Bitmap.createBitmap(2400,2400 ,Bitmap.Config.ARGB_8888))
        var drawings = ArrayList<Drawing?>()
        var lastColor = currentBrush
        var lastWidth = 0.1f+2f*3f

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myCanvas = findViewById<MyCanvasView>(R.id.view2)

        findViewById<Button>(R.id.galleryButton).setOnClickListener {
            val intent = Intent(this, GalleryActivity::class.java)
            bitmapHolder.savedBitmap = viewToBitmap(myCanvas)

            startActivity(intent)
        }
        val drawButton = findViewById<ImageButton>(R.id.drawButton)
        drawButton.setOnClickListener{
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.settingsContainer, DrawMenuFragment.newInstance())
            fragmentTransaction.commit()
        }
        val eraseButton = findViewById<ImageButton>(R.id.eraseButton)
        eraseButton.setOnClickListener{
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.settingsContainer, EraserMenuFragment.newInstance())
            fragmentTransaction.commit()
            myCanvas.setColor(ResourcesCompat.getColor( resources, R.color.colorBackground, null))
        }
        val clearButton = findViewById<ImageButton>(R.id.clearingButton)
        clearButton.setOnClickListener{
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.settingsContainer, ClearMenuFragment.newInstance())
            fragmentTransaction.commit()
        }
        val saveButton = findViewById<ImageButton>(R.id.savingButton)
        saveButton.setOnClickListener{
            bitmapHolder.savedBitmap = viewToBitmap(myCanvas)
            val fragmentManager: FragmentManager = supportFragmentManager
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.settingsContainer, SaveMenuFragment.newInstance())
            fragmentTransaction.commit()
        }


        }

    fun viewToBitmap(view: View): Bitmap {
        val bitmap = Bitmap.createBitmap(bitmapHolder.savedBitmap)
        val canvas = Canvas(bitmap)
        view.draw(canvas)
        return bitmap
    }

    override fun onIntPass(data: Int) {
        lastColor = data
        findViewById<MyCanvasView>(R.id.view2).setColor(data)

    }
    override fun onFloatPass(data: Float) {
        findViewById<MyCanvasView>(R.id.view2).setStrokeWidth(data)
        lastWidth = data
    }
}

