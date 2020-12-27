package com.shevchenkovtwo.homework

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shevchenkovtwo.homework.data.loadMovies
import com.shevchenkovtwo.homework.databinding.FragmentMoviesListBinding
import kotlinx.coroutines.*


class MoviesListFragment : Fragment() {

    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    private var recyclerView: RecyclerView? = null
    private var spanCount: Int = 2
    private var scope = CoroutineScope(Dispatchers.Default + Job())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        fragmentMoviesListBinding = binding
        spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            ORIENTATION_VERTICAL else ORIENTATION_HORIZONTAL
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = fragmentMoviesListBinding!!.moviesList
        scope.launch {
            loadData(requireContext())
        }
    }

    private suspend fun loadData(context: Context) = withContext(Dispatchers.Main) {
        val movies = loadMovies(context)
        recyclerView?.let {
            it.adapter = MoviesAdapter(movies)
            it.layoutManager =
                GridLayoutManager(requireContext(), spanCount, RecyclerView.VERTICAL, false)
        }
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        recyclerView = null
        super.onDestroyView()
    }

    companion object Orientation {
        const val ORIENTATION_VERTICAL = 2
        const val ORIENTATION_HORIZONTAL = 4
    }
}
