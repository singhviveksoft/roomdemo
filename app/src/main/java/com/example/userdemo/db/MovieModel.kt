package com.example.userdemo.db

import androidx.room.Entity
import androidx.room.Index
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import java.io.Serializable


@Entity
data class MovieModel(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val imageUrl: String,
    val name: String,
    val category: String,
    val desc: String):Serializable
