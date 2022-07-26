package com.example.kickscartel.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kickscartel.FetchedSneaker
import com.example.kickscartel.ProfileOptions
import com.example.kickscartel.R
import com.example.kickscartel.adapters.LastSeenRecyclerViewAdapter
import com.example.kickscartel.adapters.ProfileHandler
import com.example.kickscartel.adapters.ProfileRecyclerAdapter
import com.example.kickscartel.databinding.FragmentProfileBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class ProfileFragment : Fragment(), ProfileHandler {

    private var _binding : FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private var profileManagerLayout: RecyclerView.LayoutManager? = null
    private var profileAdapter: RecyclerView.Adapter<ProfileRecyclerAdapter.ViewHolder>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        profileManagerLayout = LinearLayoutManager(this.context, RecyclerView.VERTICAL,false)
        _binding = FragmentProfileBinding.inflate(inflater, container,false)
        val root: View = binding.root
        val profileRecyclerView = binding.profileRecycler
        profileRecyclerView.layoutManager = profileManagerLayout
        profileAdapter = ProfileRecyclerAdapter(this)
        profileRecyclerView.adapter = profileAdapter
        binding.profileLogOutButton.setOnClickListener { view ->
            Firebase.auth.signOut()
            val loginFragment = LoginFragment()
            val transaction =  fragmentManager?.beginTransaction()
            transaction?.replace(R.id.ic_wrapper, loginFragment)?.commit()
        }

        return root
    }

    override fun itemSelected(row: ProfileOptions) {
        TODO("Not yet implemented")
    }

}