package com.example.projektni2

import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.google.firebase.database.DataSnapshot
import android.graphics.BitmapFactory
import android.util.Base64
import com.example.projektni2.MainActivity.Companion.drawings
import java.lang.Exception

class GalleryActivity : AppCompatActivity() {
    private lateinit var rootNode : FirebaseDatabase
    private lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        findViewById<Button>(R.id.backButton).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            drawings.clear()
            startActivity(intent)

        }
        rootNode = FirebaseDatabase.getInstance("https://gallery-5ab90-default-rtdb.europe-west1.firebasedatabase.app/")
        reference = rootNode.getReference("drawings")


        val drawingsView: RecyclerView = findViewById(R.id.drawingsView)

        reference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (ds in snapshot.children) {
                    val dateTime = ds.child("dateTime").value
                    val savedBitmap = stringToBitMap(ds.child("savedBitmap").value as String?)
                    drawings.add(Drawing(dateTime as String?, savedBitmap))
                }
                drawingsView.layoutManager = LinearLayoutManager(this@GalleryActivity)
                val myAdapter = DrawingRecyclerAdapter(drawings)
                drawingsView.visibility = View.VISIBLE
                drawingsView.adapter = myAdapter
                drawingsView.setHasFixedSize(true)

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, error.message)
            }

        })
        //todo
        findViewById<Button>(R.id.downloadButton).setOnClickListener{
            //download image
        }
        findViewById<Button>(R.id.deleteButton).setOnClickListener{
            //delete from database
        }


    }
    fun stringToBitMap(encodedString: String?): Bitmap? {
        return try {
            val encodeByte: ByteArray = Base64.decode(encodedString, Base64.DEFAULT)
            BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.size)
        } catch (e: Exception) {
            e.message
            null
        }
    }
}