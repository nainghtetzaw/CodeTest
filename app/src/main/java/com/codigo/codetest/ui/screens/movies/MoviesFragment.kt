package com.codigo.codetest.ui.screens.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.codetest.databinding.FragmentMoviesBinding
import com.codigo.codetest.baseClasses.BaseFragment
import com.codigo.codetest.ui.recyclerview.movies.MovieDelegate
import com.codigo.codetest.ui.recyclerview.movies.MoviesAdapter
import com.codigo.codetest.ui.screens.MovieViewObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class MoviesFragment : BaseFragment<MoviesViewModel>(), MovieDelegate {

    override val viewModel: MoviesViewModel by viewModels()
    private val upcomingAdapter : MoviesAdapter by lazy { MoviesAdapter(this) }
    private val popularAdapter : MoviesAdapter by lazy { MoviesAdapter(this) }
    private lateinit var binding : FragmentMoviesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        if (viewModel.isFragmentFinished) {
            viewModel.isLoading.removeObservers(viewLifecycleOwner)
            viewModel.toastMessage.removeObservers(viewLifecycleOwner)
        }
    }

    override fun setupUi() {
        setUpRecyclerViews()
        setUpObservers()
        viewModel.fetchPopularMovies()
        viewModel.fetchUpcomingMovies()
    }

    override fun setupListeners() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchPopularMovies()
            viewModel.fetchUpcomingMovies()
        }
    }

    override fun onTapMovie(movieId: Int, isFav : Boolean) {
        viewModel.isFragmentFinished = true
        findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movieId, isFav))
    }

    override fun onTapFav(movie : MovieViewObject) {
        viewModel.addMovieToFav(movie)
//        viewModel.fetchUpcomingMovies()
//        viewModel.fetchPopularMovies()
    }

    private fun setUpRecyclerViews() {
        with(binding) {
            rvUpcoming.adapter = upcomingAdapter
            rvUpcoming.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            rvPopular.adapter = popularAdapter
            rvPopular.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setUpObservers() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isRefreshing = it
        }
        viewModel.toastMessage.observe(viewLifecycleOwner) { showLongSnackBar(it) }
        viewModel.popularMoviesData.observe(viewLifecycleOwner) {
            popularAdapter.setNewData(it)
        }
        viewModel.upcomingMoviesData.observe(viewLifecycleOwner) {
            upcomingAdapter.setNewData(it)
        }
    }
}