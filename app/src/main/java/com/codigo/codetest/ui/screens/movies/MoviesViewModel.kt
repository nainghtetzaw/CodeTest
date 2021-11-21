package com.codigo.codetest.ui.screens.movies

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codigo.codetest.baseClasses.BaseViewModel
import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.mappers.toMovieViewObject
import com.codigo.codetest.extensions.errorMessage
import com.codigo.codetest.repositories.MovieRepository
import com.codigo.codetest.ui.screens.MovieViewObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@ExperimentalCoroutinesApi
class MoviesViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val popularMoviesData : MutableLiveData<List<MovieViewObject>> = MutableLiveData()
    val upcomingMoviesData : MutableLiveData<List<MovieViewObject>> = MutableLiveData()

    fun fetchPopularMovies() {
        viewModelScope.launch {
            movieRepository.getPopularMovies()
                .catch {
                    Log.e("Popular_Movie", it.errorMessage())
                    toastMessage.postValue(it.localizedMessage)
                }
                .collect { state ->
                    when(state) {
                        is StateFulData.Success -> {
                            isLoading.value = false
                            popularMoviesData.postValue(state.result.map { it.toMovieViewObject() })
                        }
                        is StateFulData.Error -> toastMessage.postValue(state.msg)
                        is StateFulData.Loading -> isLoading.value = true
                    }
                }
        }
    }

    fun fetchUpcomingMovies() {
        viewModelScope.launch {
            movieRepository.getUpcomingMovies()
                .catch {
                    Log.e("Upcoming_movies", it.errorMessage())
                    toastMessage.postValue(it.localizedMessage)
                }
                .collect { state ->
                    when(state) {
                        is StateFulData.Success -> {
                            isLoading.value = false
                            upcomingMoviesData.postValue(state.result.map { it.toMovieViewObject() })
                        }
                        is StateFulData.Error -> toastMessage.postValue(state.msg)
                        is StateFulData.Loading -> isLoading.value = true
                    }
                }
        }
    }

    fun addMovieToFav(movie : MovieViewObject) {
        viewModelScope.launch {
            movieRepository.addMovieToFav(movie.id, movie.isFavMovie)
            Log.e("FavFlag", movie.isFavMovie.toString())
            movieRepository.getPopularMoviesFromDatabase()
                .collect{ popularMoviesData.value = it.map {movie -> movie.toMovieViewObject() } }
            movieRepository.getUpcomingMoviesFromDatabase()
                .collect{ upcomingMoviesData.value = it.map {movie -> movie.toMovieViewObject()} }
        }
    }

}