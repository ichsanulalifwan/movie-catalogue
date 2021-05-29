package com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(
    tableName = "tv_genre_entities",
    primaryKeys = ["genreId", "tvId"],
    foreignKeys = [ForeignKey(
        entity = TvShowEntity::class,
        parentColumns = ["tvId"],
        childColumns = ["tvId"]
    )],
    indices = [Index(value = ["genreId"]),
        Index(value = ["tvId"])]
)
data class TvGenreEntity(
    @NonNull
    @ColumnInfo(name = "tvId")
    var tvId: Int,

    @NonNull
    @ColumnInfo(name = "genreId")
    var genreId: Int,

    @NonNull
    @ColumnInfo(name = "genre_name")
    var genreName: String
)
