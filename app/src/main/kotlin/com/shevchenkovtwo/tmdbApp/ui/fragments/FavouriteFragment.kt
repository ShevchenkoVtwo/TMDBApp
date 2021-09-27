package com.shevchenkovtwo.tmdbApp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.shevchenkovtwo.tmdbApp.databinding.FragmentFavouriteBinding

class FavouriteFragment : Fragment() {

    private var fragmentFavouriteBinding: FragmentFavouriteBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        fragmentFavouriteBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Snackbar.make(view, "Will be updated soon", Snackbar.LENGTH_SHORT).show()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        fragmentFavouriteBinding = null
        super.onDestroy()
    }

}
