package com.example.kickscartel.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.Brands
import com.example.kickscartel.Categories
import com.example.kickscartel.R

class BrandsRecyclerAdapter(val brandHandler: BrandHandler): RecyclerView.Adapter<BrandsRecyclerAdapter.ViewHolder>() {

    val brandsArray = arrayOf(
        Brands.Adidas,
        Brands.Yeezy,
        Brands.Nike,
        Brands.NewBalance,
        Brands.Supreme
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.categorie_card, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: BrandsRecyclerAdapter.ViewHolder, position: Int) {
        holder.brandTitle.text = brandsArray[position].toString()
        holder.headerImage.setImageResource(R.drawable.guava)
    }

    override fun getItemCount(): Int {
        return brandsArray.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var headerImage: ImageView
        var brandTitle: TextView

        init {
            headerImage = itemView.findViewById(R.id.categories_card_image)
            brandTitle = itemView.findViewById(R.id.categories_card_title)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                brandHandler.brandSelected(brandsArray[position])
            }
        }
    }
}

interface BrandHandler {
    fun brandSelected(brand: Brands)
}
