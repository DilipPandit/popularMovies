package com.popularmovies.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.popularmovies.R
import com.popularmovies.database.PopularMoviesDao
import com.popularmovies.models.PopularMoviesResponse
import com.popularmovies.models.Result
import com.popularmovies.networking.NetworkingHelper
import com.popularmovies.utils.AppConstants
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository(
    private val context: Context,
    private val PAGE_NUMBER: Int,
    private val moviesDao: PopularMoviesDao
) {
    val movieList = MutableLiveData<List<Result>>()
    fun getPopularMovies(): LiveData<List<Result>>? {
        var request = NetworkingHelper.getInstance().getMovieList(AppConstants.KEY, PAGE_NUMBER)
        if (AppConstants.isNetworkAvailable(context)) {
            request.enqueue(object : Callback<PopularMoviesResponse?> {
                override fun onResponse(
                    call: Call<PopularMoviesResponse?>,
                    response: Response<PopularMoviesResponse?>
                ) {
                    Log.d(context.getString(R.string.sucess), response.body()?.results.toString())
                    GlobalScope.launch {
                        moviesDao.insert(response.body()?.results!!)
                    }

                    movieList.postValue(response.body()?.results!!)
                }

                override fun onFailure(call: Call<PopularMoviesResponse?>, t: Throwable) {
                    Log.d(context.getString(R.string.exception), t.toString())
                    movieList.postValue(null)
                }
            })
        } else {
            GlobalScope.launch {
                movieList.postValue(moviesDao.getMovies())
            }
        }
        return movieList;
    }

}