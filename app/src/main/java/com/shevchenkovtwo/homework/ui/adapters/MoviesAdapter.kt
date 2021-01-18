package com.shevchenkovtwo.homework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.shevchenkovtwo.homework.R
import com.shevchenkovtwo.homework.data.models.MovieDetails
import com.shevchenkovtwo.homework.databinding.ListViewMovieItemBinding
import com.shevchenkovtwo.homework.ui.viewholders.MovieViewHolder


class MoviesAdapter : PagingDataAdapter<MovieDetails, MovieViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding =
            ListViewMovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
            val bundle = bundleOf(selectedMovie to item)
            holder.itemView.setOnClickListener {
                it.findNavController()
                    .navigate(R.id.action_fragmentMoviesList_to_movieDetailFragment, bundle)
            }
        }
    }

    companion object {
        const val selectedMovie = "SELECTED_MOVIE"
        private val COMPARATOR = object : DiffUtil.ItemCallback<MovieDetails>() {
            override fun areItemsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
                return oldItem.tmdbId == newItem.tmdbId
            }

            override fun areContentsTheSame(oldItem: MovieDetails, newItem: MovieDetails): Boolean {
                return newItem == oldItem
            }
        }
    }

}

fun Float.calculateMovieRating(): Float = this / 2