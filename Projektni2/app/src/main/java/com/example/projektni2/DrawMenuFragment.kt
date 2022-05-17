package com.example.projektni2

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import com.example.projektni2.MainActivity.Companion.lastColor
import com.example.projektni2.MainActivity.Companion.lastWidth
import top.defaults.colorpicker.ColorPickerPopup
import top.defaults.colorpicker.ColorPickerPopup.ColorPickerObserver


class DrawMenuFragment : Fragment() {

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
        val view = inflater.inflate(R.layout.fragment_draw_menu, container,
            false)
        val seek = view.findViewById<SeekBar>(R.id.seekBar)
        seek.progress = ((lastWidth-0.1)/3f).toInt()
        val imageButton=view.findViewById<ImageButton>(R.id.imageButton)
        imageButton.setBackgroundColor(lastColor)
        imageButton.setOnClickListener { v ->
            ColorPickerPopup.Builder(view.context)
                .initialColor(lastColor)
                .enableBrightness(false)
                .enableAlpha(false)
                .okTitle("Choose")
                .cancelTitle( "Cancel")
                .showIndicator(true)
                .showValue(true)
                .build()
                .show(
                    v,
                    object : ColorPickerObserver() {
                        override fun onColorPicked(color: Int) {
                            imageButton.setBackgroundColor(color)
                            passIntData(color)
                        }
                    }
                )
        }

        val resetButton = view.findViewById<ImageButton>(R.id.resetButton)
        resetButton.setOnClickListener {
            imageButton.setBackgroundColor(Color.BLACK)
            passIntData(Color.BLACK)
        }
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

    fun passIntData(data: Int){
        dataPasser.onIntPass(data)
    }
    fun passFloatData(data: Float){
        dataPasser.onFloatPass(data)
    }


        companion object {
        @JvmStatic
        fun newInstance() =
            DrawMenuFragment().apply {
                }
            }
    }
