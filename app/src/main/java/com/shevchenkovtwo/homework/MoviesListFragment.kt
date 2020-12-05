package com.shevchenkovtwo.homework

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shevchenkovtwo.homework.databinding.FragmentMoviesListBinding


class MoviesListFragment : Fragment() {

    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    private lateinit var recyclerView: RecyclerView
    private var spanCount: Int = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        fragmentMoviesListBinding = binding
        spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            AppConstants.ORIENTATION_VERTICAL else AppConstants.ORIENTATION_HORIZONTAL
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val movies = DataSource().getMovies()
        recyclerView = fragmentMoviesListBinding!!.moviesList
        recyclerView.adapter = MoviesAdapter(movies)
        recyclerView.layoutManager =
            GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        super.onDestroyView()
    }
}