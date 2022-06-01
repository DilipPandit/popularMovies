package com.popularmovies.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.popularmovies.R
import com.popularmovies.models.Result

@Database(entities = [Result::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun resultDao(): PopularMoviesDao;

    companion object {
        @Volatile
        private var INSTANT: DataBase? = null
        fun getDataBase(context: Context): DataBase {
            if (INSTANT == null) {
                synchronized(this) {
                    INSTANT = Room.databaseBuilder(
                        context.applicationContext,
                        DataBase::class.java,
                        context.applicationContext.getString(R.string.moviedb)
                    ).build()
                }
            }
            return INSTANT!!

        }
    }
}