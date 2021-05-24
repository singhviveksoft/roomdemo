package com.example.userdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.userdemo.databinding.MovieItemBinding
import com.example.userdemo.db.MovieModel
import kotlinx.android.synthetic.main.activity_movie_detail.view.*

class MovieAdapter(val onClick:itemClick):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    var list= arrayListOf<MovieModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
    val inflater=LayoutInflater.from(parent.context)
        var view=MovieItemBinding.inflate(inflater,parent,false)
        return MovieViewHolder(view,onClick)
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



    inner  class MovieViewHolder(val binding:MovieItemBinding,val click: itemClick ) :RecyclerView.ViewHolder(binding.root){
        init {

                itemView.setOnClickListener {
                    click.onClick(list[adapterPosition])
                }





        }


        fun bind(list: MovieModel,click: itemClick){
            binding.movie=list


        }

    }


    interface itemClick{
        fun onClick(movieModel: MovieModel)
        fun onLongClick(movieModel: MovieModel)
    }


}


