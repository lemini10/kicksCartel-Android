package com.example.kickscartel.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kickscartel.FetchedSneaker
import com.example.kickscartel.R
import com.example.kickscartel.databinding.FragmentBrowseBinding
import com.example.kickscartel.databinding.FragmentItemDetailBinding

class itemDetailFragment() : Fragment() {

    private var _binding : FragmentItemDetailBinding? = null
    private val binding get() = _binding!!

    lateinit var sneaker: FetchedSneaker

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemDetailBinding.inflate(inflater, container,false)
        val root: View = binding.root
        binding.detailItemBrandText.text = sneaker.brand
        binding.detailItemCompleteText.text = sneaker.completeName
        binding.detailItemDetailsText.text = sneaker.description
        binding.itemDetailImage.setImageResource(R.drawable.guava)
        return root
    }
}