package com.example.userdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.userdemo.databinding.MovieItemBinding
import com.example.userdemo.db.MovieModel

class MovieAdapter(val onClick:itemClick):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var list= arrayListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val inflater=LayoutInflater.from(parent.context)
        var view=MovieItemBinding.inflate(inflater,parent,false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(list[position],onClick)
    }


    override fun getItemCount(): Int {
        return list.size
    }
    fun addAll(list:List<MovieModel>){
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    class MovieViewHolder(val binding:MovieItemBinding) :RecyclerView.ViewHolder(binding.root){

        fun bind(list: MovieModel,click: itemClick){
            binding.movie=list
            itemView.setOnClickListener {
                click.onClick(list)
            }
        }

    }


    interface itemClick{
        fun onClick(movieModel: MovieModel)
    }

}