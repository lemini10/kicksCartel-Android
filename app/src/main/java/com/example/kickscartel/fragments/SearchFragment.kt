package com.example.kickscartel.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.DataManager
import com.example.kickscartel.FetchedSneaker
import com.example.kickscartel.R
import com.example.kickscartel.TrendingRecyclerAdapter
import com.example.kickscartel.adapters.ItemHandler
import com.example.kickscartel.adapters.ProfileRecyclerAdapter
import com.example.kickscartel.adapters.SearchRecyclerAdapter
import com.example.kickscartel.databinding.FragmentProfileBinding
import com.example.kickscartel.databinding.FragmentSearchBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SearchFragment : Fragment(), ItemHandler {

    private var _binding : FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var profileManagerLayout: RecyclerView.LayoutManager? = null
    private var profileAdapter: RecyclerView.Adapter<SearchRecyclerAdapter.ViewHolder>? = null
    var sneakersFetched: ArrayList<FetchedSneaker> = ArrayList()
    private val dataManager = DataManager()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileManagerLayout = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)
        _binding = FragmentSearchBinding.inflate(inflater, container,false)
        val root: View = binding.root
        val profileRecyclerView = binding.searchRecyclerView
        profileRecyclerView.layoutManager = profileManagerLayout
        setHasOptionsMenu(true)
        this.sneakersFetched = dataManager.fetchSneakers {
            profileAdapter = SearchRecyclerAdapter(this, this.sneakersFetched)
            profileRecyclerView.adapter = profileAdapter
        }

        return root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.menu.search)
        val profileRecyclerView = binding.searchRecyclerView
        if (searchItem != null) {
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if (p0!!.isNotEmpty()) {
                        var displayList: ArrayList<FetchedSneaker> = ArrayList()
                        val search = p0.toLowerCase()
                        sneakersFetched.forEach {
                            if (it.completeName.toLowerCase().contains(search)) {
                                displayList.add(it)
                            }
                        }
                        profileRecyclerView.adapter?.notifyDataSetChanged()
                    }
                    return true
                }
            })
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun itemSelected(item: String) {
    }

}