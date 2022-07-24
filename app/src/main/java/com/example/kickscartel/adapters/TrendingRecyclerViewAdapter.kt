package com.example.kickscartel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrendingRecyclerAdapter(val sneakerHandler: TrendingHandler, val sneakersArray: Array<FetchedSneaker>): RecyclerView.Adapter<TrendingRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: TrendingRecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = sneakersArray[position].completeName
        holder.itemPrice.text = sneakersArray[position].price
        holder.headerImage.setImageResource(R.drawable.guava)
    }

    override fun getItemCount(): Int {
        return sneakersArray.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var headerImage: ImageView
        var itemTitle: TextView
        var itemPrice: TextView

        init {
            headerImage = itemView.findViewById(R.id.item_card_header_image)
            itemTitle = itemView.findViewById(R.id.item_card_title)
            itemPrice = itemView.findViewById(R.id.item_card_price)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                sneakerHandler.itemSelected(sneakersArray[position])
            }
        }
    }
}

interface TrendingHandler {
    fun itemSelected(new: FetchedSneaker)
}
