package com.shevchenkovtwo.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.shevchenkovtwo.homework.databinding.FragmentMoviesListBinding


class MoviesListFragment : Fragment() {

    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        fragmentMoviesListBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMoviesListBinding?.let { it ->
            it.movieLayout.root.setOnClickListener { view ->
                view.findNavController().navigate(R.id.movieDetailFragment)
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        super.onDestroyView()
    }
}