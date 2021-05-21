package com.example.userdemo.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.userdemo.R
import com.example.userdemo.databinding.ActivityMovieDetailBinding
import com.example.userdemo.db.MovieModel

class MovieDetailActivity : AppCompatActivity() {
    companion object{
        val move="moveItem"
    }
  private  lateinit var binding:ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_movie_detail)
        val moveItem=intent.getSerializableExtra(move) as MovieModel
        binding.model=moveItem

    }

}