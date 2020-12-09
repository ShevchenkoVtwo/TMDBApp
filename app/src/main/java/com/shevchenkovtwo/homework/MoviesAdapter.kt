package com.shevchenkovtwo.homework


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.shevchenkovtwo.homework.AppConstants.selectedMovie
import com.shevchenkovtwo.homework.databinding.ListViewMovieItemBinding


class MoviesAdapter(private var movies: List<Movie>) :
    RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {

    class MovieViewHolder(private val binding: ListViewMovieItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieLayout.movieTitle.text = movie.name
            binding.movieLayout.duration.text = movie.duration
            binding.movieLayout.pg.text = movie.pg
            binding.movieLayout.movieRating.rating = movie.rating
            binding.movieLayout.reviews.text = movie.reviews
            binding.movieLayout.tag.text = movie.tags
            if (movie.favorite) {
                binding.movieLayout.favourite.setImageResource(R.drawable.ic_favorite)
            } else
                binding.movieLayout.favourite.setImageResource(R.drawable.ic_not_favorite)
            binding.movieLayout.movieBackground.setImageResource(movie.imageForList)
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
}