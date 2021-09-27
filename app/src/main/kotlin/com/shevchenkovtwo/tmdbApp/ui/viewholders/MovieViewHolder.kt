package com.shevchenkovtwo.tmdbApp.ui.viewholders

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.tmdbApp.R
import com.shevchenkovtwo.tmdbApp.databinding.ListViewMovieItemBinding
import com.shevchenkovtwo.tmdbApp.domain.models.Movie
import com.shevchenkovtwo.tmdbApp.ui.fragments.NowPlayingMoviesFragmentDirections

class MovieViewHolder(private val binding: ListViewMovieItemBinding, var movie: Movie? = null) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movieLayout.movieTitle.text = movie.name
        binding.movieLayout.pg.text = itemView.context.getString(R.string.pg, movie.minimumAge)
        binding.movieLayout.movieRating.rating = movie.ratings
        binding.movieLayout.reviews.text =
            itemView.context.getString(R.string.reviews, movie.numberOfRatings)
        binding.movieLayout.duration.text =
            itemView.context.getString(R.string.duration, movie.runtime)
        binding.movieLayout.tag.text = movie.genres.joinToString { it.name }
        binding.movieLayout.movieBackground.load(movie.poster) {
            crossfade(true)
            error(R.drawable.ic_error)
            placeholder(R.drawable.gradient)
        }
        this.movie = movie
    }

    init {
        binding.root.setOnClickListener { view ->
            movie?.let { movie ->
                view.findNavController().saveState()
                val action =
                    NowPlayingMoviesFragmentDirections.actionMoviesListFragmentToMovieDetailFragment(
                        movie
                    )
                view.findNavController().navigate(action)
            }
        }
    }

}