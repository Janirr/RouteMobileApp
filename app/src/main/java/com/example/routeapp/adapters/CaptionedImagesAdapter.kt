package com.example.routeapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.routeapp.R

class CaptionedImagesAdapter(
    private var captions: Array<String?>,
    private var imageIds: Array<Int?>
) : RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder>() {
    private var listener: Listener? = null

    interface Listener {
        fun onClick(position: Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.info_image)
        val textView: TextView = itemView.findViewById(R.id.info_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val cv = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_captioned_image, parent, false) as CardView
        return ViewHolder(cv)
    }

    override fun getItemCount(): Int {
        return captions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cardView = holder.itemView as CardView
        val imageView = holder.imageView
        val textView = holder.textView
        val drawable =
            ContextCompat.getDrawable(cardView.context, imageIds[position] ?: R.drawable.image)
        imageView.setImageDrawable(drawable)
        imageView.contentDescription = captions[position]
        textView.text = captions[position]
        cardView.setOnClickListener {
            listener?.onClick(position)
        }
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }
}
