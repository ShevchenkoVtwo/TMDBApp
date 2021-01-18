package com.shevchenkovtwo.homework.ui.fragments

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.shevchenkovtwo.homework.databinding.FragmentMoviesListBinding
import com.shevchenkovtwo.homework.ui.adapters.MoviesAdapter
import com.shevchenkovtwo.homework.ui.viewmodels.MoviesViewModel
import com.shevchenkovtwo.homework.ui.viewmodels.MoviesViewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MoviesListFragment : Fragment() {

    private var fragmentMoviesListBinding: FragmentMoviesListBinding? = null
    private var spanCount: Int = 2
    private var job: Job? = null
    private val viewModel: MoviesViewModel by viewModels { MoviesViewModelFactory() }
    private val adapter = MoviesAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentMoviesListBinding.inflate(inflater, container, false)
        fragmentMoviesListBinding = binding
        spanCount = if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            ORIENTATION_VERTICAL else ORIENTATION_HORIZONTAL
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
        initAdapter()
        initRecyclerView()
    }

    private fun initViewModel() {
        job?.cancel()
        job = lifecycleScope.launch {
            viewModel.loadNowPlayingMovies().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        fragmentMoviesListBinding?.moviesList?.apply {
            layoutManager =
                GridLayoutManager(requireContext(), spanCount)
        }
    }

    private fun initAdapter() {
        fragmentMoviesListBinding?.apply {
            moviesList.adapter = adapter
            adapter.addLoadStateListener { loadState ->
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                loadingDataProgressBar.isVisible = loadState.append is LoadState.Loading

                val errorState = loadState.source.append as? LoadState.Error
                    ?: loadState.source.prepend as? LoadState.Error
                    ?: loadState.append as? LoadState.Error
                    ?: loadState.prepend as? LoadState.Error
                errorState?.let {
                    Toast.makeText(requireContext(), "${it.error}", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        fragmentMoviesListBinding = null
        job = null
        super.onDestroyView()
    }

    companion object {
        const val ORIENTATION_VERTICAL = 2
        const val ORIENTATION_HORIZONTAL = 4
    }
}