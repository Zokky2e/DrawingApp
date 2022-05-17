package com.example.projektni2

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import com.example.projektni2.MainActivity.Companion.lastWidth

class EraserMenuFragment : Fragment() {
    lateinit var dataPasser: OnDataPass

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_eraser_menu, container,false)
        val seek = view.findViewById<SeekBar>(R.id.eraserSeekBar)
        seek.progress = ((lastWidth -0.1f)/3f).toInt()
        seek?.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
            }
            override fun onStartTrackingTouch(seek: SeekBar) {}
            override fun onStopTrackingTouch(seek: SeekBar) {
                passFloatData((0.1f+seek.progress*3f))
            }
        })
        return view
    }

    fun passFloatData(data: Float){
        dataPasser.onFloatPass(data)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            EraserMenuFragment().apply {
            }
    }
}