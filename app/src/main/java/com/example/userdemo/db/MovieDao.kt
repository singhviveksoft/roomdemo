package com.example.userdemo.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
   suspend fun insertMovie(movieEntity:List<MovieModel>)

   @Query("DELETE FROM MovieModel")
   suspend fun deletMovie()

    @Update
    suspend fun updateMovie(movieEntity:List<MovieModel>)

    @Query("SELECT * FROM MovieModel")
    fun getAllMovies():LiveData<List<MovieModel>>

    @Query("SELECT EXISTS(SELECT * FROM MovieModel)")
    fun hasItem(): Boolean

}