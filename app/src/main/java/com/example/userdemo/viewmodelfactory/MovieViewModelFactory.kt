package com.example.userdemo.viewmodelfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.userdemo.db.MovieDataBase
import com.example.userdemo.repository.UserRepository
import com.example.userdemo.viewmodel.MovieViewModel

class MovieViewModelFactory(val userRepository: UserRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieViewModel::class.java)){
            return MovieViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknow MoviesViewModel ")

    }
}