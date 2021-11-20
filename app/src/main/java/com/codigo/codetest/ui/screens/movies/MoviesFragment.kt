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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
    }

    override fun setupListeners() {

    }

    override fun onTapMovie(movieId: Int) {
        viewModel.isFragmentFinished = true
        findNavController().navigate(MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(movieId))
    }

    override fun onTapFav(movieId: Int, isFavMovie: Boolean) {

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
            if(it){
                viewModel.toastMessage.postValue("Loading")
            }
        }
        viewModel.toastMessage.observe(viewLifecycleOwner) { showLongSnackBar(it) }
        viewModel.popularMoviesData.observe(viewLifecycleOwner) {
            popularAdapter.setNewData(it)
        }
    }
}