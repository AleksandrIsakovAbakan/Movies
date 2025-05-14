package com.example.movies3.database

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import org.jetbrains.annotations.NotNull

@Dao
interface MoviesDao {

    @SuppressLint("KotlinNullnessAnnotation")
    @Query("SELECT * FROM movies_entity")
    @NotNull
    fun getMovies(): LiveData<List<MoviesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun add(moviesEntity: MoviesEntity): Completable

    @Query("DELETE FROM movies_entity WHERE id = :id")
    fun remove(id: Int): Completable

    @Query("DELETE FROM movies_entity")
    fun removeAll()

    @Query("SELECT * FROM movies_entity WHERE id = :position")
    fun getMovie(position: Int): LiveData<MoviesEntity>

    @Query("SELECT COUNT(*) FROM movies_entity")
    fun size(): Int
}