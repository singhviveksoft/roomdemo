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

class MovieViewModel(val userRepository: UserRepository): ViewModel() {

    var movie=MutableLiveData<List<MovieModel>>()

//    val movieName:LiveData<MovieModel>
//    get() = us

    var getAllMovieDataBase:Boolean= false
//init {
//    getAllMovieList()
//}
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
                      //  getAllMovieDataBase=   userRepository.hasitem()

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

 fun checkDatabase():Boolean{
    CoroutineScope(Dispatchers.IO).launch {
        getAllMovieDataBase=  userRepository.hasitem()
    }
     return getAllMovieDataBase
}



    }

