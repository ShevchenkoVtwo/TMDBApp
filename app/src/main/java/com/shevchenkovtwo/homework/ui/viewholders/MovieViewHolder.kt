package com.shevchenkovtwo.homework.ui.viewholders

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.homework.R
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.databinding.ListViewMovieItemBinding
import com.shevchenkovtwo.homework.ui.adapters.calculateMovieRating
import com.shevchenkovtwo.homework.ui.fragments.MoviesListFragmentDirections


class MovieViewHolder(
    private val binding: ListViewMovieItemBinding,
    var movie: MovieDetails? = null
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(movie: MovieDetails) {
        binding.movieLayout.movieTitle.text = movie.title
        binding.movieLayout.pg.text =
            itemView.context.getString(R.string.pg, if (movie.age) 16 else 13)
        binding.movieLayout.movieRating.rating = movie.ratings.calculateMovieRating()
        binding.movieLayout.reviews.text =
            itemView.context.getString(R.string.reviews, movie.numberOfRatings)
        binding.movieLayout.duration.text =
            itemView.context.getString(R.string.duration, movie.runtime)
        binding.movieLayout.tag.text = movie.genres.joinToString { it.name }
        binding.movieLayout.movieBackground.load(movie.poster) {
            crossfade(true)
            error(R.drawable.error)
            placeholder(R.drawable.gradient)
        }
    }

    init {
        binding.root.setOnClickListener {
            movie?.let { movie ->
                val action =
                    MoviesListFragmentDirections.actionFragmentMoviesListToMovieDetailFragment(movie)
                it.findNavController().navigate(action)
            }
        }
    }
}
