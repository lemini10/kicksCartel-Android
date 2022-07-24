package com.example.kickscartel.adapters

import com.example.kickscartel.FetchedSneaker
import com.example.kickscartel.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LastSeenRecyclerViewAdapter(val sneakerHandler: LastSeenHandler, val sneakersArray: Array<FetchedSneaker>): RecyclerView.Adapter<LastSeenRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastSeenRecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: LastSeenRecyclerViewAdapter.ViewHolder, position: Int) {
        holder.itemBrand.text = sneakersArray[position].brand
        holder.itemCompleteName.text = sneakersArray[position].completeName
        holder.itemSize.text = sneakersArray[position].size
        holder.headerImage.setImageResource(R.drawable.guava)
    }

    override fun getItemCount(): Int {
        return sneakersArray.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var headerImage: ImageView
        var itemCompleteName: TextView
        var itemSize: TextView
        var itemBrand: TextView

        init {
            headerImage = itemView.findViewById(R.id.cart_card_image_shoe)
            itemCompleteName = itemView.findViewById(R.id.cart_card_complete_title)
            itemSize = itemView.findViewById(R.id.cart_card_size_title)
            itemBrand = itemView.findViewById(R.id.cart_card_brand_title)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                sneakerHandler.lastSeenSelected(sneakersArray[position])
            }
        }
    }
}

interface LastSeenHandler {
    fun lastSeenSelected(itemSeen: FetchedSneaker)
}
