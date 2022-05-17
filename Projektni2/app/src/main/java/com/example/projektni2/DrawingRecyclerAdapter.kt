package com.example.projektni2

import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class DrawingRecyclerAdapter(private val items: ArrayList<Drawing?>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return DrawingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.drawing_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is DrawingViewHolder ->{
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }



    class DrawingViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        private val drawing1: ImageView = itemView.findViewById(R.id.image1)
        private val date1: TextView = itemView.findViewById(R.id.date1)

        fun bind(drawing: Drawing?){
            val d = BitmapDrawable(drawing?.savedBitmap)
                Glide
                    .with(itemView.context)
                    .load(d)
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(drawing1)
            date1.text = drawing?.dateTime
        }
    }
}