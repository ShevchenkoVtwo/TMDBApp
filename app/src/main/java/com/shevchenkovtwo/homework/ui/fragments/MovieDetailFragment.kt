package com.shevchenkovtwo.homework.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.shevchenkovtwo.homework.R
import com.shevchenkovtwo.homework.data.Movie
import com.shevchenkovtwo.homework.databinding.FragmentMovieDetailBinding
import com.shevchenkovtwo.homework.ui.adapters.ActorsAdapter
import com.shevchenkovtwo.homework.ui.adapters.calculateMovieRating
import com.shevchenkovtwo.homework.ui.viewmodles.MoviesViewModel


class MovieDetailFragment : Fragment() {

    private var fragmentMovieDetailBinding: FragmentMovieDetailBinding? = null
    private val viewModel: MoviesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        fragmentMovieDetailBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.movie.let{ movie ->
            initViews(movie)
            if (checkActorsList(movie))
                Snackbar.make(view, getString(R.string.error_message), Snackbar.LENGTH_SHORT).show()
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initViews(movie: Movie) {
        fragmentMovieDetailBinding?.apply {
            movieName.text = movie.title
            movieStoryline.text = movie.overview
            movieRating.rating = movie.ratings.calculateMovieRating()
            pg.text = getString(R.string.pg,movie.minimumAge)
            reviews.text = getString(R.string.reviews,movie.numberOfRatings)
            tag.text = movie.genres.joinToString { genre -> genre.name }
            mask.load(movie.backdrop)
            actors.let {
                it.adapter = ActorsAdapter(movie.actors)
                it.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
            back.setOnClickListener {
                findNavController().navigate(R.id.moviesListFragment)
            }
        }
    }

    private fun checkActorsList(movie: Movie): Boolean {
        return movie.actors.isNullOrEmpty()
    }

    override fun onDestroyView() {
        fragmentMovieDetailBinding = null
        super.onDestroyView()
    }
}