package com.popularmovies.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.popularmovies.models.Result

@Dao
interface PopularMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: List<Result>)

    @Query("SELECT * FROM popular_movies")
    suspend fun getMovies(): List<Result>
}