package com.example.projektni2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.projektni2.MainActivity.Companion.bitmapHolder
import com.example.projektni2.MainActivity.Companion.lastColor
import com.example.projektni2.MainActivity.Companion.lastWidth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SaveMenuFragment : Fragment() {

    lateinit var rootNode : FirebaseDatabase
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_save_menu, container, false)


        view.findViewById<Button>(R.id.saveButton).setOnClickListener {
            rootNode = FirebaseDatabase.getInstance("https://gallery-5ab90-default-rtdb.europe-west1.firebasedatabase.app/")
            reference = rootNode.getReference("drawings")
            val drawingHelper = DrawingHelper(bitmapHolder.savedBitmap)
            reference.push().setValue(drawingHelper)
            MainActivity.drawings.clear()
            exitClearMenu()
        }
        view.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            exitClearMenu()

        }
        return view


    }

    private fun exitClearMenu() {
        val manager = requireActivity().supportFragmentManager
        manager.beginTransaction().replace(
            R.id.settingsContainer,
            DrawMenuFragment.newInstance()
        ).commit()

    }
    companion object {

        @JvmStatic
        fun newInstance() =
            SaveMenuFragment().apply {
            }
    }
}