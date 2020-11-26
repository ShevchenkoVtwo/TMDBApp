package com.shevchenkovtwo.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.shevchenkovtwo.homework.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {

    private var fragmentMovieDetailBinding: FragmentMovieDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        fragmentMovieDetailBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMovieDetailBinding?.back?.setOnClickListener {
            findNavController().navigate(R.id.moviesListFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

}