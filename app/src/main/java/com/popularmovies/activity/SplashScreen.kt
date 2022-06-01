package com.popularmovies.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.popularmovies.R
import com.popularmovies.adapters.PopularMovieListAdapter
import com.popularmovies.database.DataBase
import com.popularmovies.database.PopularMoviesDao
import com.popularmovies.databinding.ActivityMainBinding
import com.popularmovies.models.Result
import com.popularmovies.repository.AppRepository

class SplashScreen : AppCompatActivity() {
    private var pageNumber = 1
    lateinit var binding: ActivityMainBinding
    lateinit var movieList: List<Result>
    lateinit var popularMovieListAdapter: PopularMovieListAdapter
    lateinit var movieListViewModel: MovieListViewModel
    lateinit var repository: AppRepository
    lateinit var moviesDataBase: DataBase
    lateinit var moviesDao: PopularMoviesDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        movieList = emptyList()
        moviesDataBase = DataBase.getDataBase(this)
        moviesDao = moviesDataBase.resultDao()
        repository = AppRepository(this, pageNumber, moviesDao)
        movieListViewModel =
            ViewModelProvider(this, MovieListViewModelFactory(repository)).get(
                MovieListViewModel::class.java
            )
        movieListViewModel.getMovieList()?.observe(this) {
            it.let {
                movieList = it
                setupAdapter()
            }

        }
    }

    private fun setupAdapter() {
        popularMovieListAdapter = PopularMovieListAdapter(this, movieList)
        binding.rvMovieList.layoutManager = LinearLayoutManager(this)
        binding.rvMovieList.adapter = popularMovieListAdapter

    }
}