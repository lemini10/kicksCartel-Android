package com.example.kickscartel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter(val news: List<BrowseCategoriesModel>,
              private val onItemClicked: (position: Int) -> Unit): RecyclerView.Adapter<MainViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.browse_card, parent, false)
        return MainViewHolder(v,onItemClicked)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val article: BrowseCategoriesModel = news.get(position)
        holder.render(article)
    }

    override fun getItemCount(): Int {
        return news.count()
    }
}

class MainViewHolder(
    val view: View,
    private val onItemClicked: (position: Int) -> Unit): RecyclerView.ViewHolder(view), View.OnClickListener {


    init {
        view.setOnClickListener(this)
    }

    fun render(new: BrowseCategoriesModel) {
        view.findViewById<ImageView>(R.id.headerCardImage)
        view.findViewById<TextView>(R.id.headerCardText).text = new.title
    }

    override fun onClick(p0: View?) {
        val position = adapterPosition
        onItemClicked(position)
    }
}