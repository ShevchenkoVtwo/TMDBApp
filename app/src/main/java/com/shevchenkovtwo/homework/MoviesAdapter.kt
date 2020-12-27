package com.shevchenkovtwo.homework


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shevchenkovtwo.homework.data.Movie
import com.shevchenkovtwo.homework.databinding.ListViewMovieItemBinding


class MoviesAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ListViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieLayout.movieTitle.text = movie.title
            binding.movieLayout.pg.text = setPGText(movie.minimumAge)
            binding.movieLayout.movieRating.rating = calculateMovieRating(movie.ratings)
            binding.movieLayout.reviews.text = setReviewsText(movie.numberOfRatings)
            binding.movieLayout.duration.text = setDurationText(movie.runtime)
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
        holder.itemView.setOnClickListener {
            selectedMovie = movies[position]
            it.findNavController().navigate(R.id.action_fragmentMoviesList_to_movieDetailFragment)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    companion object Constants {
        var selectedMovie: Movie? = null
    }
}

fun calculateMovieRating(movieRating: Float) = movieRating / 2
fun setPGText(pg: Int): String = "$pg +"
fun setReviewsText(reviews: Int): String = "$reviews reviews"
fun setDurationText(duration: Int): String = "$duration min"