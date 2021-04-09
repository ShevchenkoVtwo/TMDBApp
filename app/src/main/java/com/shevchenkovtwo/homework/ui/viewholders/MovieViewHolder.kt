package com.shevchenkovtwo.homework.ui.viewholders

import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.homework.R
import com.shevchenkovtwo.homework.databinding.ListViewMovieItemBinding
import com.shevchenkovtwo.homework.basemodels.Movie
import com.shevchenkovtwo.homework.ui.fragments.MoviesListFragmentDirections


class MovieViewHolder(private val binding: ListViewMovieItemBinding, var movie: Movie? = null) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movieLayout.movieTitle.text = movie.name
        binding.movieLayout.pg.text = itemView.context.getString(R.string.pg, movie.age)
        binding.movieLayout.movieRating.rating = movie.ratings
        binding.movieLayout.reviews.text = itemView.context.getString(R.string.reviews, movie.numberOfRatings)
        binding.movieLayout.duration.text = itemView.context.getString(R.string.duration, movie.runtime)
        binding.movieLayout.tag.text = movie.genres.joinToString { it.name }
        binding.movieLayout.movieBackground.load(movie.poster) {
            crossfade(true)
            error(R.drawable.ic_error)
            placeholder(R.drawable.gradient)
        }
    }

    init {
        binding.root.setOnClickListener { view ->
            movie?.let { movie ->
                view.findNavController().saveState()
                val action = MoviesListFragmentDirections.actionFragmentMoviesListToMovieDetailFragment(movie)
                view.findNavController().navigate(action)
            }
        }
    }

}