package com.example.kickscartel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.*
import com.example.kickscartel.adapters.LastSeenHandler
import com.example.kickscartel.adapters.LastSeenRecyclerViewAdapter
import com.example.kickscartel.databinding.FragmentCartBinding

class cartFragment : Fragment(), LastSeenHandler {

    private var _binding : FragmentCartBinding? = null
    private val binding get() = _binding!!
    private var cartManagerLayout: RecyclerView.LayoutManager? = null
    private var cartAdapter: RecyclerView.Adapter<LastSeenRecyclerViewAdapter.ViewHolder>? = null
    private val dataManager = DataManager()

    var cartSneakers: ArrayList<FetchedSneaker> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        cartManagerLayout = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)
        _binding = FragmentCartBinding.inflate(inflater, container,false)
        val root: View = binding.root
        val cartRecyclerView = binding.cartItemsRecycler
        cartRecyclerView.layoutManager = cartManagerLayout

        this.cartSneakers = dataManager.fetchUserSneakers(UserPreferences.Cart){
            cartAdapter = LastSeenRecyclerViewAdapter(this,sneakersArray = this.cartSneakers)
            cartRecyclerView.adapter = cartAdapter
        }
        return root
    }

    override fun lastSeenSelected(itemSeen: FetchedSneaker) {
        TODO("Not yet implemented")
    }
}