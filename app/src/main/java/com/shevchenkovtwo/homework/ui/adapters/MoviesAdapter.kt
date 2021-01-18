package com.shevchenkovtwo.homework.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
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
            holder.movie = item
        }
    }

    companion object {
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