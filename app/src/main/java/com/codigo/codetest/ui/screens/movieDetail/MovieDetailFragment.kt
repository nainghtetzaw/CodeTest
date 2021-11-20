package com.codigo.codetest.ui.screens.movieDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.codigo.codetest.databinding.FragmentMovieDetailBinding
import com.codigo.codetest.baseClasses.BaseFragment
import com.codigo.codetest.ui.recyclerview.casts.CastAdapter
import com.codigo.codetest.ui.recyclerview.genres.GenreAdapter

class MovieDetailFragment : BaseFragment<MovieDetailViewModel>() {

    override val viewModel: MovieDetailViewModel by viewModels()
    private val genreAdapter : GenreAdapter by lazy { GenreAdapter() }
    private val castAdapter : CastAdapter by lazy { CastAdapter() }
    private lateinit var binding : FragmentMovieDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun setupUi() {
        setupRecyclerViews()
    }

    override fun setupListeners() {
        with(binding) {
            ivBack.setOnClickListener { findNavController().navigateUp() }
            ivFav.setOnClickListener {  }
            swipeRefresh.setOnRefreshListener {  }
        }
    }

    private fun setupRecyclerViews() {
        with(binding) {
            rvGenres.adapter = genreAdapter
            rvGenres.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            rvCasts.adapter = castAdapter
            rvCasts.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

}