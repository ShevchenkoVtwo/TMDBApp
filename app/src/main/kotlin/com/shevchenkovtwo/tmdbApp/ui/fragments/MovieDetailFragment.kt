package com.shevchenkovtwo.tmdbApp.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.tmdbApp.R
import com.shevchenkovtwo.tmdbApp.databinding.FragmentMovieDetailBinding
import com.shevchenkovtwo.tmdbApp.domain.models.Movie
import com.shevchenkovtwo.tmdbApp.ui.adapters.ActorsAdapter
import com.shevchenkovtwo.tmdbApp.ui.utils.BaseFragment

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>() {

    private val fragmentArguments: MovieDetailFragmentArgs by navArgs()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMovieDetailBinding = FragmentMovieDetailBinding::inflate

    override fun setupViews() {
        setClickListenerForNavigation()
        initViews(fragmentArguments.movie)
    }

    private fun setClickListenerForNavigation() {
        binding?.apply {
            back.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initViews(movie: Movie) {
        binding?.apply {
            movieName.text = movie.name
            movieStoryline.text = movie.overview
            movieRating.rating = movie.ratings
            pg.text = getString(R.string.pg, movie.minimumAge)
            reviews.text = getString(R.string.reviews, movie.numberOfRatings)
            tag.text = movie.genres.joinToString { genre -> genre.name }
            mask.load(movie.backdrop)
            actors.apply {
                adapter = ActorsAdapter(movie.actors)
                layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }
    }

}
