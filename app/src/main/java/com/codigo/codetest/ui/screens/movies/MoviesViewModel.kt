package com.codigo.codetest.ui.screens.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codigo.codetest.baseClasses.BaseViewModel
import com.codigo.codetest.data.models.mappers.toMovieViewObject
import com.codigo.codetest.repositories.MovieRepository
import com.codigo.codetest.ui.screens.MovieViewObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val popularMoviesData : MutableLiveData<List<MovieViewObject>> = MutableLiveData()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            movieRepository.getPopularMovies()
                .onStart { isLoading.value = true }
                .catch { toastMessage.postValue(it.localizedMessage) }
                .collect { movies ->
                    popularMoviesData.value = movies.map { it.toMovieViewObject() }
                }
        }
    }

}