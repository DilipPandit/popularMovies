package com.popularmovies.activity

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.popularmovies.models.Result
import com.popularmovies.repository.AppRepository

class MovieListViewModel(private val appRepository: AppRepository) : ViewModel() {

    fun getMovieList(): LiveData<List<Result>>? {
        return appRepository.getPopularMovies()
    }
}