package com.example.userdemo.view

import android.content.Context
import android.content.Intent
import android.database.DatabaseUtils
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.userdemo.R
import com.example.userdemo.`interface`.Api
import com.example.userdemo.adapter.MovieAdapter
import com.example.userdemo.databinding.ActivityMainBinding
import com.example.userdemo.db.MovieDataBase
import com.example.userdemo.db.MovieModel
import com.example.userdemo.repository.UserRepository
import com.example.userdemo.viewmodel.MovieViewModel
import com.example.userdemo.viewmodelfactory.MovieViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),MovieAdapter.itemClick{
    lateinit var binding:ActivityMainBinding
    lateinit var viewModel: MovieViewModel
    lateinit var dataBase: MovieDataBase
    lateinit var adapter: MovieAdapter
    var list= emptyList<MovieModel>()


    val retrofit=Api.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        dataBase = MovieDataBase.getInstance(this)

        viewModel = ViewModelProvider(this, MovieViewModelFactory(UserRepository(retrofit, dataBase)))
                .get(MovieViewModel::class.java)
        //
        adapter = MovieAdapter(this)
        binding.movieRecyclerView.adapter = adapter

        //   adapter= MovieAdapter(list)


        if (checkInternet()) {
            viewModel.getAllMovieList()

    }
        else{
            binding.progressBar.visibility=View.VISIBLE
        }

        viewModel.getMovies().observe(this, Observer {
        if (!it.isNullOrEmpty()){
            adapter.addAll(it)
            binding.progressBar.visibility=View.INVISIBLE

        }

        })
        checkInternet()
//        CoroutineScope(Dispatchers.IO).launch {
//        }

    }
    fun checkInternet():Boolean{
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        return isConnected
    }

    override fun onClick(movieModel: MovieModel) {
        val intent=Intent(this,MovieDetailActivity::class.java)
        intent.putExtra(MovieDetailActivity.move,movieModel)
        startActivity(intent)
    }


}
