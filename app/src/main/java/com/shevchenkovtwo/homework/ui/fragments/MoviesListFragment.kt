package com.shevchenkovtwo.homework.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shevchenkovtwo.homework.databinding.FragmentMoviesListBinding
import com.shevchenkovtwo.homework.ui.adapters.MoviesAdapter
import com.shevchenkovtwo.homework.ui.viewmodles.MoviesViewModel


class MoviesListFragment : Fragment() {

    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    private val moviesListMoviesViewModel: MoviesViewModel by viewModels()
    private var spanCount: Int = 2

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        fragmentMoviesListBinding = binding
        spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            ORIENTATION_VERTICAL else ORIENTATION_HORIZONTAL
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(fragmentMoviesListBinding)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(binding: FragmentMoviesListBinding?) {
        moviesListMoviesViewModel.loadMoviesData()
        moviesListMoviesViewModel.moviesList.observe(viewLifecycleOwner) { movies ->
            binding?.moviesList?.let {
                it.adapter = MoviesAdapter(movies)
                it.layoutManager =
                    GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
            }
        }
    }


    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        super.onDestroyView()
    }

    companion object  {
        const val ORIENTATION_VERTICAL = 2
        const val ORIENTATION_HORIZONTAL = 4
    }
}