package com.shevchenkovtwo.homework.ui.fragments

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.homework.R
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.databinding.FragmentMovieDetailBinding
import com.shevchenkovtwo.homework.network.ResponsesLogging
import com.shevchenkovtwo.homework.ui.adapters.ActorsAdapter
import com.shevchenkovtwo.homework.ui.adapters.calculateMovieRating
import com.shevchenkovtwo.homework.ui.viewmodels.MovieDetailsViewModel
import com.shevchenkovtwo.homework.ui.viewmodels.MoviesViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MovieDetailFragment : Fragment() {

    private var fragmentMovieDetailBinding: FragmentMovieDetailBinding? = null
    private val args: MovieDetailFragmentArgs by navArgs()
    private var job: Job? = null
    private val viewModel: MovieDetailsViewModel by viewModels { MoviesViewModelFactory() }
    private val adapter = ActorsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        fragmentMovieDetailBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListenerForNavigation()
        initViews(args.movie)
        initLoad()
        initViewModel()
        initRecyclerView()
    }

    private fun setClickListenerForNavigation() {
        fragmentMovieDetailBinding?.apply {
            back.setOnClickListener {
                findNavController().navigate(R.id.moviesListFragment)
            }
        }
    }

    private fun initLoad() {
        args.movie.apply {
            viewModel.loadActors(tmdbId)
        }
    }

    private fun initViewModel() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.stateFlow.collectLatest { response ->
                when (response) {
                    is ResponsesLogging.Success -> {
                        adapter.differ.submitList(response.data)
                        fragmentMovieDetailBinding?.loadingDataProgressBar?.isVisible = false
                    }
                    is ResponsesLogging.Loading -> {
                        fragmentMovieDetailBinding?.loadingDataProgressBar?.isVisible = true
                    }
                    is ResponsesLogging.Error -> {
                        fragmentMovieDetailBinding?.loadingDataProgressBar?.isVisible = false
                        response.message.let {
                            Log.e(TAG, "Something went wrong!")
                        }
                    }
                }
            }
        }
    }

    private fun initViews(movie: MovieDetails) {
        fragmentMovieDetailBinding?.apply {
            movieName.text = movie.title
            movieStoryline.text = movie.overview
            movieRating.rating = movie.ratings.calculateMovieRating()
            pg.text = getString(R.string.pg, if (movie.age) 16 else 13)
            reviews.text = getString(R.string.reviews, movie.numberOfRatings)
            tag.text = movie.genres.joinToString { genre -> genre.name }
            mask.load(movie.backdrop)
        }
    }

    private fun initRecyclerView() {
        fragmentMovieDetailBinding?.apply {
            actors.let {
                it.adapter = adapter
                it.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }
    }

    override fun onDestroyView() {
        fragmentMovieDetailBinding = null
        job = null
        super.onDestroyView()
    }
}