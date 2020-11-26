package com.shevchenkovtwo.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.shevchenkovtwo.homework.databinding.FragmentMoviesListBinding


class MoviesListFragment : Fragment() {

    private lateinit var fragmentMoviesListBinding: FragmentMoviesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        fragmentMoviesListBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMoviesListBinding.movieLayout.root.setOnClickListener {
            it.findNavController().navigate(R.id.movieDetailFragment)
        }
        super.onViewCreated(view, savedInstanceState)
    }

}