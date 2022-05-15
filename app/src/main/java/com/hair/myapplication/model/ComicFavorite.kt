package com.hair.myapplication.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hair.myapplication.units.Const


@Entity(tableName = Const.TABLE_NAME)
data class ComicFavorite(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = Const.ID_COLUMN)
    var id: Int = 0,
    @ColumnInfo(name = Const.NAME_COLUMN)
    var name: String,
    @ColumnInfo(name = Const.IMAGE_COLUMN)
    var image: String,
    @ColumnInfo(name = Const.LINK_COLUMN)
    var link: String,

    @ColumnInfo(name = Const.CHAP_COLUMN)
    var chap: String
)
