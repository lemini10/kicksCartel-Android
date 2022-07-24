package com.example.kickscartel.adapters

import com.example.kickscartel.FetchedSneaker
import com.example.kickscartel.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.Brands
import com.example.kickscartel.ProfileOptions

class ProfileRecyclerAdapter(val handler: ProfileHandler): RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>() {

    val profileRowsArray = arrayOf(
        ProfileOptions.Information,
        ProfileOptions.Buying,
        ProfileOptions.Shipping,
        ProfileOptions.Payments,
        ProfileOptions.Tutorial
    )

    val profileDetailsArray = arrayOf(
        "Modify password,name,size,email",
        "Track,modify or view orders",
        "Save,edit or remove address",
        "Add or remove payment methods",
        "See app tutorials"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileRecyclerAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.profile_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ProfileRecyclerAdapter.ViewHolder, position: Int) {
        holder.itemTitle.text = profileRowsArray[position].toString()
        holder.itemDetails.text = profileDetailsArray[position]

        when(profileRowsArray[position]) {
            ProfileOptions.Information -> holder.headerImage.setImageResource(R.drawable.ic_baseline_person_24)
            ProfileOptions.Buying -> holder.headerImage.setImageResource(R.drawable.ic_baseline_shopping_cart_24)
            ProfileOptions.Shipping -> holder.headerImage.setImageResource(R.drawable.ic_baseline_home_24)
            ProfileOptions.Payments -> holder.headerImage.setImageResource(R.drawable.ic_baseline_credit_card_24)
            ProfileOptions.Tutorial -> holder.headerImage.setImageResource(R.drawable.ic_baseline_question_answer_24)
        }
    }

    override fun getItemCount(): Int {
        return profileRowsArray.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var headerImage: ImageView
        var itemTitle: TextView
        var itemDetails: TextView

        init {
            headerImage = itemView.findViewById(R.id.profile_row_image)
            itemTitle = itemView.findViewById(R.id.profile_row_title)
            itemDetails = itemView.findViewById(R.id.profile_row_details)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                handler.itemSelected(profileRowsArray[position])
            }
        }
    }
}

interface ProfileHandler {
    fun itemSelected(row: ProfileOptions)
}
