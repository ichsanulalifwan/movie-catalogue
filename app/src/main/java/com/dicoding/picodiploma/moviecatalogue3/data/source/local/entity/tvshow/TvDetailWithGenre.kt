package com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow

import androidx.room.Embedded
import androidx.room.Relation

data class TvDetailWithGenre(
    @Embedded
    var mTv: TvShowEntity,

    @Relation(parentColumn = "tvId", entityColumn = "tvId")
    var mGenre: List<TvGenreEntity>
)
