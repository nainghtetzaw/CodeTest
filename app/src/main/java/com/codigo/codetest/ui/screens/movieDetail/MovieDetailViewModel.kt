package com.codigo.codetest.ui.screens.movieDetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.codigo.codetest.baseClasses.BaseViewModel
import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.mappers.toMovieViewObject
import com.codigo.codetest.extensions.errorMessage
import com.codigo.codetest.repositories.MovieRepository
import com.codigo.codetest.ui.screens.CastViewObject
import com.codigo.codetest.ui.screens.MovieViewObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val movieData : MutableLiveData<MovieViewObject> = MutableLiveData()

    fun fetchMovieData(movieId : Int) {
        viewModelScope.launch {
              movieRepository.getMovieDetail(movieId)
                  .collect { state ->
                      when(state) {
                          is StateFulData.Success -> {
                              isLoading.postValue(false)
                              movieData.postValue(state.result.toMovieViewObject())
                          }
                          is StateFulData.Loading -> isLoading.value = true
                          is StateFulData.Error -> toastMessage.postValue(state.msg)
                      }
                  }
        }
    }

}