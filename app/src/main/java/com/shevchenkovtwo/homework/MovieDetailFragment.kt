package com.shevchenkovtwo.homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.google.android.material.snackbar.Snackbar
import com.shevchenkovtwo.homework.data.Movie
import com.shevchenkovtwo.homework.databinding.FragmentMovieDetailBinding


class MovieDetailFragment : Fragment() {

    private var fragmentMovieDetailBinding: FragmentMovieDetailBinding? = null
    private var movieDetailViewModel: MovieDetailViewModel? = null
    private var recyclerView: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        fragmentMovieDetailBinding = binding
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieDetailViewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)
        movieDetailViewModel?.let { movieViewModel ->
            movieViewModel.getSelectedMovie().observe(viewLifecycleOwner, { movie ->
                fragmentMovieDetailBinding?.let { binding ->
                    initView(movie, binding)
                    navigateBack(binding)
                    if (checkData(movie)){
                        Snackbar.make(view, getString(R.string.error_message), Snackbar.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }

    private fun initView(movie: Movie, binding: FragmentMovieDetailBinding) {
        binding.apply {
            movieName.text = movie.title
            movieStoryline.text = movie.overview
            movieRating.rating = calculateMovieRating(movie.ratings)
            pg.text = setPGText(movie.minimumAge)
            reviews.text = setReviewsText(movie.numberOfRatings)
            tag.text = movie.genres.joinToString { genre -> genre.name }
            mask.load(movie.backdrop)
            recyclerView = actors
            recyclerView?.let {
                it.adapter = ActorsAdapter(movie.actors)
                it.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            }
        }
    }

    private fun checkData(movie: Movie): Boolean {
        return  movie.actors.isNullOrEmpty()
    }

    private fun navigateBack(binding: FragmentMovieDetailBinding) {
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.moviesListFragment)
        }
    }

    override fun onDestroyView() {
        fragmentMovieDetailBinding = null
        recyclerView = null
        super.onDestroyView()
    }
}