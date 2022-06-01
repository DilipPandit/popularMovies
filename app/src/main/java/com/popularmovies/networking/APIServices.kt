package com.popularmovies.networking

import com.popularmovies.models.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIServices {
    @GET("3/movie/popular?")
    fun getMovieList(@Query("api_key")api_key:String,@Query("page")page : Int
    ): Call<PopularMoviesResponse>
}