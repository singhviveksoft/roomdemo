package com.example.userdemo.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.userdemo.db.MovieDataBase
import com.example.userdemo.db.MovieModel
import com.example.userdemo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(val userRepository: UserRepository): ViewModel() {

    var movie=MutableLiveData<List<MovieModel>>()

    val movieList:LiveData<List<MovieModel>>
    get() = movie

//    val getAllMovieDataBase:LiveData<List<MovieModel>>
//    get() = userRepository.getAllMovie()

    fun getAllMovieList(){
        viewModelScope.launch {
          //  Log.i("MyViewModelThread","Thread name ${Thread.currentThread().name}")

            var result:List<MovieModel>?=null

            withContext(Dispatchers.IO) {

                userRepository.deleteMovie()

                    result = userRepository.getMovieRespr()

                    if (!result.isNullOrEmpty()) {
                        //  //
                      //  userRepository.deleteMovie()

                        userRepository.insertMovie(result!!)
                        userRepository.updateMovie(result!!)

                    }

                }
            }

//            db.movieDao.insertMovie(result[0])
//         var output=  db.movieDao.getAllMovies()
//            movie.value=output
//          //  movie.value=result
        }




    fun getMovies():LiveData<List<MovieModel>>{

        return  userRepository.getAllMovie()
    }

    }

