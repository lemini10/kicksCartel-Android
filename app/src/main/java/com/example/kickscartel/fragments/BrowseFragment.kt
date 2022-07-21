package com.example.kickscartel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.FetchedNews
import com.example.kickscartel.NewsHandler
import com.example.kickscartel.NewsRecyclerAdapter
import com.example.kickscartel.R
import com.example.kickscartel.databinding.FragmentBrowseBinding

class BrowseFragment : Fragment(), NewsHandler {


    private var _binding : FragmentBrowseBinding? = null
    private val binding get() = _binding!!

    private var managerLayout: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<NewsRecyclerAdapter.ViewHolder>? = null
    val new = FetchedNews("Adidas","Guava","Yeexy 350 Onyx releasing soon")
    val newsFetched = arrayOf(new)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        managerLayout = LinearLayoutManager(this.context,OrientationHelper.HORIZONTAL,false)
        _binding = FragmentBrowseBinding.inflate(inflater, container,false)
        val root: View = binding.root
        val recyclerView = binding.newsBrowseRecycler
        recyclerView.layoutManager = managerLayout
        adapter = NewsRecyclerAdapter(newHandler = this,newsArray = newsFetched)
        recyclerView.adapter = adapter

        return root
    }

    override fun newSelected(new: FetchedNews) {
        TODO("Not yet implemented")
    }
}