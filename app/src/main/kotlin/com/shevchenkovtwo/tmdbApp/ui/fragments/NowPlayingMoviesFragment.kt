package com.shevchenkovtwo.tmdbApp.ui.fragments

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.shevchenkovtwo.tmdbApp.databinding.FragmentMoviesListBinding
import com.shevchenkovtwo.tmdbApp.ui.adapters.MoviesAdapter
import com.shevchenkovtwo.tmdbApp.ui.utils.BaseFragment
import com.shevchenkovtwo.tmdbApp.ui.viewmodels.NowPlayingMoviesViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest

class NowPlayingMoviesFragment : BaseFragment<FragmentMoviesListBinding>() {

    @ExperimentalCoroutinesApi
    private val viewModel: NowPlayingMoviesViewModel by viewModels()
    private val adapter = MoviesAdapter()
    private var job: Job? = null

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMoviesListBinding =
        FragmentMoviesListBinding::inflate

    @ExperimentalCoroutinesApi
    override fun setupViews() {
        initViewModel()
        initAdapter()
        initRecyclerView()
    }

    @ExperimentalCoroutinesApi
    private fun initViewModel() {
        job?.cancel()
        job = lifecycleScope.launchWhenStarted {
            viewModel.nowPlayingMovies.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        binding?.apply {
            moviesList.adapter = adapter
            adapter.addLoadStateListener { loadState ->
                progressBar.isVisible = loadState.source.refresh is LoadState.Loading
                loadingDataProgressBar.isVisible = loadState.source.append is LoadState.Loading

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

    private fun initRecyclerView() {
        val spanCount =
            if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
                ORIENTATION_VERTICAL else ORIENTATION_HORIZONTAL
        binding?.apply {
            moviesList.layoutManager = GridLayoutManager(requireContext(), spanCount)
        }
    }

    override fun onDestroyView() {
        job = null
        super.onDestroyView()
    }

    companion object {
        const val ORIENTATION_VERTICAL = 2
        const val ORIENTATION_HORIZONTAL = 4
    }

}
