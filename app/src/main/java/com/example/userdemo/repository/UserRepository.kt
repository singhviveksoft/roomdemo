package com.example.userdemo.repository

import androidx.lifecycle.LiveData
import com.example.userdemo.`interface`.Api
import com.example.userdemo.db.MovieDao
import com.example.userdemo.db.MovieDataBase
import com.example.userdemo.db.MovieModel

class UserRepository(val api: Api,val db: MovieDataBase) {
    suspend fun getMovieRespr()=api.getMovie()

    suspend fun insertMovie(movie:List<MovieModel>)=db.movieDao.insertMovie(movie)
    suspend fun deleteMovie()=db.movieDao.deletMovie()
    suspend fun updateMovie(movie:List<MovieModel>)=db.movieDao.updateMovie(movie)

    fun getAllMovie():LiveData<List<MovieModel>>{
      return  db.movieDao.getAllMovies()
    }

    fun hasitem():Boolean{
    return db.movieDao.hasItem()
}
}