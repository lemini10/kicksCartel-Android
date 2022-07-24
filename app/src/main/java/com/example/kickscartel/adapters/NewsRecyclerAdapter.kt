package com.example.kickscartel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsRecyclerAdapter(val newHandler: NewsHandler, val newsArray: Array<FetchedNews>): RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsRecyclerAdapter.ViewHolder, position: Int) {
        holder.newsTitle.text = newsArray[position].title
        holder.newsCategorie.text = newsArray[position].categorie
        holder.headerImage.setImageResource(R.drawable.guava)
    }

    override fun getItemCount(): Int {
        return newsArray.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var headerImage: ImageView
        var newsCategorie: TextView
        var newsTitle: TextView

        init {
            headerImage = itemView.findViewById(R.id.header_image_card)
            newsCategorie = itemView.findViewById(R.id.categorie_news_card)
            newsTitle = itemView.findViewById(R.id.title_news_card)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                newHandler.newSelected(newsArray[position])
            }
        }
    }
}

interface NewsHandler {
    fun newSelected(new: FetchedNews)
}
