package com.example.kickscartel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.*
import com.example.kickscartel.databinding.FragmentBrowseBinding

class BrowseFragment : Fragment(), NewsHandler, CategoriesHandler {


    private var _binding : FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    private var managerLayout: RecyclerView.LayoutManager? = null
    private var categoriesManagerLayout: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>? = null
    private var categoriesAdapter: RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder>? = null
    val new = FetchedNews("Adidas","Guava","Yeexy 350 Onyx releasing soon")
    val newsFetched = arrayOf(new)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        managerLayout = LinearLayoutManager(this.context, RecyclerView.HORIZONTAL,false)
        categoriesManagerLayout = LinearLayoutManager(this.context,RecyclerView.HORIZONTAL,false)
        _binding = FragmentBrowseBinding.inflate(inflater, container,false)
        val root: View = binding.root
        val recyclerView = binding.newsBrowseRecycler
        val categoriesRecyclerView = binding.categoriesBrowseRecycler
        recyclerView.layoutManager = managerLayout
        categoriesRecyclerView.layoutManager = categoriesManagerLayout
        adapter = NewsRecyclerAdapter(newHandler = this,newsArray = newsFetched)
        categoriesAdapter = CategoriesRecyclerAdapter(this)
        recyclerView.adapter = adapter
        categoriesRecyclerView.adapter = categoriesAdapter

        return root
    }

    override fun newSelected(new: FetchedNews) {
        TODO("Not yet implemented")
    }

    override fun categorieSelected(new: Categories) {
        TODO("Not yet implemented")
    }
}