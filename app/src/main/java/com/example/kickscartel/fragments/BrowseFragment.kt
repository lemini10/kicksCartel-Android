package com.example.kickscartel.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.*
import com.example.kickscartel.adapters.BrandHandler
import com.example.kickscartel.adapters.BrandsRecyclerAdapter
import com.example.kickscartel.adapters.LastSeenHandler
import com.example.kickscartel.adapters.LastSeenRecyclerViewAdapter
import com.example.kickscartel.databinding.FragmentBrowseBinding

class BrowseFragment : Fragment(), NewsHandler, CategoriesHandler, TrendingHandler, BrandHandler,
    LastSeenHandler {

    private var _binding : FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    private var managerLayout: RecyclerView.LayoutManager? = null
    private var categoriesManagerLayout: RecyclerView.LayoutManager? = null
    private var trendingManagerLayout: RecyclerView.LayoutManager? = null
    private var brandsManagerLayout: RecyclerView.LayoutManager? = null
    private var lastSeenManagerLayout: RecyclerView.LayoutManager? = null

    private var adapter: RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>? = null
    private var categoriesAdapter: RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder>? = null
    private var trendingAdapter: RecyclerView.Adapter<TrendingRecyclerAdapter.ViewHolder>? = null
    private var brandsAdapter: RecyclerView.Adapter<BrandsRecyclerAdapter.ViewHolder>? = null
    private var lastSeenAdapter: RecyclerView.Adapter<LastSeenRecyclerViewAdapter.ViewHolder>? = null

    private val dataManager = DataManager()

    val new = FetchedNews("Adidas","Guava","Yeexy 350 Onyx releasing soon")
    val sneaker = FetchedSneaker("Jordan", "Guava", "Jordan 4 Guava Ice","180USD","Amazing super shoe",
        ImageSet("","",""), "")
    var newsFetched: ArrayList<FetchedNews> = ArrayList()
    var sneakersFetched: ArrayList<FetchedSneaker> = ArrayList()
    val sneakersSeen = arrayOf(sneaker,sneaker,sneaker,sneaker)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        managerLayout = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL,false)
        categoriesManagerLayout = LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        trendingManagerLayout = LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        brandsManagerLayout = LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        lastSeenManagerLayout = LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)

        _binding = FragmentBrowseBinding.inflate(inflater, container,false)
        val root: View = binding.root

        val recyclerView = binding.newsBrowseRecycler
        val categoriesRecyclerView = binding.categoriesBrowseRecycler
        val trendingRecyclerView = binding.trendingBrowseRecycler
        val brandsRecyclerView = binding.brandsBrowseRecycler
        val lastSeenRecyclerView = binding.lastSeenBrowseRecycler

        recyclerView.layoutManager = managerLayout
        categoriesRecyclerView.layoutManager = categoriesManagerLayout
        trendingRecyclerView.layoutManager = trendingManagerLayout
        brandsRecyclerView.layoutManager = brandsManagerLayout
        lastSeenRecyclerView.layoutManager = lastSeenManagerLayout

        categoriesAdapter = CategoriesRecyclerAdapter(this)
        brandsAdapter = BrandsRecyclerAdapter(this)
        lastSeenAdapter = LastSeenRecyclerViewAdapter(this,sneakersArray = this.sneakersFetched)

        categoriesRecyclerView.adapter = categoriesAdapter
        brandsRecyclerView.adapter = brandsAdapter
        lastSeenRecyclerView.adapter = lastSeenAdapter

        this.newsFetched = dataManager.fetchNews {
            adapter = NewsRecyclerAdapter(newHandler = this,newsArray = newsFetched)
            recyclerView.adapter = adapter
        }

        this.sneakersFetched = dataManager.fetchSneakers {
            trendingAdapter = TrendingRecyclerAdapter(this,sneakersArray = this.sneakersFetched)
            trendingRecyclerView.adapter = trendingAdapter
        }

        return root
    }

    override fun newSelected(new: FetchedNews) {
        Log.d("Item selected","ITEMS SELECTED")
    }

    override fun categorieSelected(new: Categories) {
        Log.d("Item selected","ITEMS SELECTED")
    }

    override fun itemSelected(new: FetchedSneaker) {
        Log.d("Item selected","ITEMS SELECTED")
    }

    override fun brandSelected(brand: Brands) {
        Log.d("Item selected","ITEMS SELECTED")
    }

    override fun lastSeenSelected(itemSeen: FetchedSneaker) {
        Log.d("Item selected","ITEMS SELECTED")
    }
}