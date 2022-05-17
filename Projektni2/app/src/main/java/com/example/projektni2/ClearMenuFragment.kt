package com.example.projektni2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import com.example.projektni2.MainActivity.Companion.lastColor
import com.example.projektni2.MainActivity.Companion.lastWidth
import com.example.projektni2.MyCanvasView.Companion.extraCanvas


class ClearMenuFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_clear_menu, container, false)

        view.findViewById<Button>(R.id.clearButton).setOnClickListener {
            extraCanvas.drawColor(ResourcesCompat.getColor(resources, R.color.colorBackground, null))
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

        fun newInstance() =
            ClearMenuFragment().apply {
            }
    }
}