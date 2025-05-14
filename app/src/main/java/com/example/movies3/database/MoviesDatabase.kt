package com.example.movies3.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(entities = [MoviesEntity::class], version = 1, exportSchema = false)
abstract class MoviesDatabase : RoomDatabase() {

    companion object {

        private var instance: MoviesDatabase? = null
        private val DB_NAME: String = "movies4.db"

        fun getInstance(application: Application): MoviesDatabase {
            if (instance == null) {
                instance = databaseBuilder(
                    application,
                    MoviesDatabase::class.java,
                    DB_NAME
                ).build()
            }
            return instance as MoviesDatabase
        }
    }

    abstract fun moviesDao(): MoviesDao?

}