package com.example.kickscartel

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CategoriesRecyclerAdapter(val categorieHandler: CategoriesHandler): RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder>() {

    val categoriesArray = arrayOf(Categories.Sneakers,Categories.Beanies,Categories.Collectibles,Categories.Shirt,Categories.Slides)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.categorie_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: CategoriesRecyclerAdapter.ViewHolder, position: Int) {
        holder.categorieTitle.text = categoriesArray[position].toString()
        holder.headerImage.setImageResource(R.drawable.guava)
    }

    override fun getItemCount(): Int {
        return categoriesArray.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var headerImage: ImageView
        var categorieTitle: TextView

        init {
            headerImage = itemView.findViewById(R.id.categories_card_image)
            categorieTitle = itemView.findViewById(R.id.categories_card_title)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                categorieHandler.categorieSelected(categoriesArray[position])
            }
        }
    }
}

interface CategoriesHandler {
    fun categorieSelected(new: Categories)
}
