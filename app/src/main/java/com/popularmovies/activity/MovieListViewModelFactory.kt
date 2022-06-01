package com.popularmovies.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.popularmovies.repository.AppRepository

class MovieListViewModelFactory(private val appRepository: AppRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(appRepository) as T
    }
}