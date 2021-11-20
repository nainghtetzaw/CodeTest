package com.codigo.codetest.repositories

import com.codigo.codetest.data.StateFulData
import com.codigo.codetest.data.models.domain.BelongsToType
import com.codigo.codetest.data.models.domain.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun getPopularMovies() : Flow<List<Movie>>

}