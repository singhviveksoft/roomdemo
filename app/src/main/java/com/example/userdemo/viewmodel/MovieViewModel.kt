package com.example.userdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdemo.db.MovieDataBase
import com.example.userdemo.db.MovieModel
import com.example.userdemo.repository.UserRepository
import kotlinx.coroutines.*

class MovieViewModel(val userRepository: UserRepository) : ViewModel() {

    var movie = MutableLiveData<List<MovieModel>>()
    var result: List<MovieModel>? = null

    var getAllMovieDataBase: Boolean = false

    val coroutineExpection = CoroutineExceptionHandler { coroutineContext, throwable ->
        println("Caught $throwable")
    }

    fun getAllMovieListApi() {
        viewModelScope.launch {


            withContext(Dispatchers.IO) {
                userRepository.deleteMovie()
                supervisorScope {
                    Log.i("supervisorScope ", "${Thread.currentThread().name}")

                    launch(coroutineExpection) {
                        Log.i("before launch", "${Thread.currentThread().name}")
                        result = userRepository.getMovieRespr()
                        Log.i("after launch", "${Thread.currentThread().name}")

                    }
                }
                if (!result.isNullOrEmpty()) {


                    userRepository.insertMovie(result!!)

                    Log.i("insert ", "${Thread.currentThread().name}")

                }

            }
        }


    }


    fun getMovieItem(): List<MovieModel>? {
        return result
    }

    fun getMovies(): LiveData<List<MovieModel>> {

        return userRepository.getAllMovie()
    }

    fun checkDatabase(): Boolean {
        CoroutineScope(Dispatchers.IO).launch {
            getAllMovieDataBase = userRepository.hasitem()
        }
        return getAllMovieDataBase
    }


}

