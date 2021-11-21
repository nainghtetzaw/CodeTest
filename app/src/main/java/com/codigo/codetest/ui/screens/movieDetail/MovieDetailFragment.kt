package com.codigo.codetest.ui.screens.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.codetest.databinding.FragmentMovieDetailBinding
import com.codigo.codetest.baseClasses.BaseFragment
import com.codigo.codetest.ui.recyclerview.genres.GenreAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<MovieDetailViewModel>() {

    override val viewModel: MovieDetailViewModel by viewModels()
    private val genreAdapter : GenreAdapter by lazy { GenreAdapter() }
    private val args : MovieDetailFragmentArgs by navArgs()
    private lateinit var binding : FragmentMovieDetailBinding

    private var isSelected : Boolean = false

    override fun onStop() {
        super.onStop()
        if(viewModel.isFragmentFinished) {
            viewModel.isLoading.removeObservers(viewLifecycleOwner)
            viewModel.toastMessage.removeObservers(viewLifecycleOwner)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
        setupRecyclerViews()
        setObservers()
        viewModel.fetchMovieData(args.movieId)
    }

    override fun setupListeners() {
        with(binding) {
            btnBack.setOnClickListener { findNavController().navigateUp() }
            btnFav.setOnClickListener {
                isSelected = !isSelected!!
                binding.isSelected = isSelected
            }
            swipeRefresh.setOnRefreshListener { viewModel.fetchMovieData(args.movieId) }
        }
    }

    private fun setupRecyclerViews() {
        with(binding) {
            rvGenres.adapter = genreAdapter
            rvGenres.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    private fun setObservers() {
        isSelected = args.isFav
        binding.isSelected = isSelected
        viewModel.isLoading.observe(viewLifecycleOwner) { binding.swipeRefresh.isRefreshing = it }
        viewModel.toastMessage.observe(viewLifecycleOwner) { showLongSnackBar(it) }
        viewModel.movieData.observe(viewLifecycleOwner) {
            binding.data = it
            genreAdapter.setNewData(it.genres)
        }
    }

}