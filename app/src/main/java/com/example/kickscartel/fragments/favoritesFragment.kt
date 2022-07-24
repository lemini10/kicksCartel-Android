package com.example.kickscartel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.FetchedSneaker
import com.example.kickscartel.ImageSet
import com.example.kickscartel.adapters.LastSeenHandler
import com.example.kickscartel.adapters.LastSeenRecyclerViewAdapter
import com.example.kickscartel.databinding.FragmentFavoritesBinding

class favoritesFragment : Fragment(), LastSeenHandler {

    private var _binding : FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private var favoritesManagerLayout: RecyclerView.LayoutManager? = null
    private var favoritesAdapter: RecyclerView.Adapter<LastSeenRecyclerViewAdapter.ViewHolder>? = null
    val sneaker = FetchedSneaker("Jordan", "Guava", "Jordan 4 Guava Ice","180USD","Amazing super shoe",
        ImageSet("","",""), "")
    val favoritesSneakers = arrayOf(sneaker,sneaker,sneaker,sneaker)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoritesManagerLayout = LinearLayoutManager(this.context,RecyclerView.VERTICAL,false)
        _binding = FragmentFavoritesBinding.inflate(inflater, container,false)
        val root: View = binding.root
        val favoritesRecyclerView = binding.favoritesItemsRecycler
        favoritesRecyclerView.layoutManager = favoritesManagerLayout
        favoritesAdapter = LastSeenRecyclerViewAdapter(this,sneakersArray = favoritesSneakers)
        favoritesRecyclerView.adapter = favoritesAdapter

        return root
    }

    override fun lastSeenSelected(itemSeen: FetchedSneaker) {
        TODO("Not yet implemented")
    }
}