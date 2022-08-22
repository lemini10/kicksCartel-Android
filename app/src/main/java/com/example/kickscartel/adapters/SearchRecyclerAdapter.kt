package com.example.kickscartel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.FetchedNews
import com.example.kickscartel.FetchedSneaker
import com.example.kickscartel.R

class SearchRecyclerAdapter(val newHandler: ItemHandler, val sneakers: ArrayList<FetchedSneaker>): RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.search_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: SearchRecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = sneakers[position].completeName
    }

    override fun getItemCount(): Int {
        return sneakers.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.search_row_text)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                newHandler.itemSelected(sneakers[position].id)
            }
        }
    }
}

interface ItemHandler {
    fun itemSelected(item: String)
}
