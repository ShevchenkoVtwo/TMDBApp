package com.shevchenkovtwo.homework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.homework.R
import com.shevchenkovtwo.homework.data.Movie
import com.shevchenkovtwo.homework.databinding.ListViewMovieItemBinding


class MoviesAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ListViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieLayout.movieTitle.text = movie.title
            binding.movieLayout.pg.text = itemView.context.getString(R.string.pg, movie.minimumAge)
            binding.movieLayout.movieRating.rating = movie.ratings.calculateMovieRating()
            binding.movieLayout.reviews.text =
                itemView.context.getString(R.string.reviews, movie.numberOfRatings)
            binding.movieLayout.duration.text =
                itemView.context.getString(R.string.duration, movie.runtime)
            binding.movieLayout.tag.text = movie.genres.joinToString { it.name }
            binding.movieLayout.movieBackground.load(movie.poster)
            //TODO Add placeholders for empty images when will be added retrofit
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ListViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
        val bundle = bundleOf(selectedMovie to movies[position])
        holder.itemView.setOnClickListener {
            it.findNavController()
                .navigate(R.id.action_fragmentMoviesList_to_movieDetailFragment, bundle)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    companion object {
        const val selectedMovie = "SELECTED_MOVIE"
    }
}

fun Float.calculateMovieRating(): Float = this / 2