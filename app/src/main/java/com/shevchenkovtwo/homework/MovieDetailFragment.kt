package com.shevchenkovtwo.homework

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.shevchenkovtwo.homework.AppConstants.selectedMovie
import com.shevchenkovtwo.homework.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {

    private var fragmentMovieDetailBinding: FragmentMovieDetailBinding? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        fragmentMovieDetailBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        fragmentMovieDetailBinding?.let {
            it.apply {
                movieName.text = selectedMovie?.name
                movieRating.rating = selectedMovie!!.rating
                selectedMovie!!.storyline?.let { storyline -> movieStoryline.setText(storyline) }
                pg.text = selectedMovie?.pg
                reviews.text = selectedMovie?.reviews
                tag.text = selectedMovie?.tags
                selectedMovie!!.imageMask?.let { image -> mask.setImageResource(image) }
                if (selectedMovie!!.favorite) {
                    favourite.setImageResource(R.drawable.ic_favorite)
                } else {
                    favourite.setImageResource(R.drawable.ic_not_favorite)
                }
                recyclerView = actors
                recyclerView.adapter =
                    selectedMovie!!.actors?.let { actors -> ActorsAdapter(actors) }
                recyclerView.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                back.setOnClickListener {
                    findNavController().navigate(R.id.moviesListFragment)
                }
            }
        }
        Snackbar.make(view, "If you see not full info about movie, it will be updated soon!", Snackbar.LENGTH_LONG)
            .setTextColor(ContextCompat.getColor(requireContext(), R.color.title_color))
            .show()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        fragmentMovieDetailBinding = null
        super.onDestroyView()
    }
}