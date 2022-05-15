package com.hair.myapplication.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Comic")
data class Comic(
    @PrimaryKey
    val name: String = "",
    val image: String = "",
    val link: String = "",
    val chap: String = ""

)
