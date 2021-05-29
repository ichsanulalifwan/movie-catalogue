package com.dicoding.picodiploma.moviecatalogue3.utils

import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.movie.MovieGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvDetailWithGenre
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvGenreEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.MovieResultsItem
import com.dicoding.picodiploma.moviecatalogue3.data.source.remote.response.TvShowResultsItem

object DataDummy {

    fun generateDummyMovies(): List<MovieResultsItem> {

        val movies = ArrayList<MovieResultsItem>()

        movies.add(
            MovieResultsItem(
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "2021-04-07",
                460465,
                "Mortal Kombat",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/nkayOAUBUu4mMvyNf9iHSUiPjF1.jpg"
            )
        )

        movies.add(
            MovieResultsItem(
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "2021-04-29",
                567189,
                "Tom Clancy's Without Remorse",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg"
            )
        )

        movies.add(
            MovieResultsItem(
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "2021",
                12,
                "Raya and the Last Dragon",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg"
            )
        )
        return movies
    }

    fun generateDummyDetailMovie(movie: MovieEntity, isFav: Boolean): MovieDetailWithGenre {
        movie.isFavorite = isFav
        return MovieDetailWithGenre(movie, generateDummyMovieGenres(movie.movieId))
    }

    fun generateLocalDummyDetailMovies(): List<MovieEntity> {

        val detailMovies = ArrayList<MovieEntity>()

        detailMovies.add(
            MovieEntity(
                460465,
                "Mortal Kombat",
                "2021-04-07",
                "Washed-up MMA fighter Cole Young, unaware of his heritage, and hunted by Emperor Shang Tsung's best warrior, Sub-Zero, seeks out and trains with Earth's greatest champions as he prepares to stand against the enemies of Outworld in a high stakes battle for the universe.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fMMrl8fD9gRCFJvsx0SuFwkEOop.jpg",
                7.6,
                "Get over here.",
                110,
                "Released",
                false
            )
        )

        detailMovies.add(
            MovieEntity(
                567189,
                "Tom Clancy's Without Remorse",
                "2021-04-29",
                "An elite Navy SEAL uncovers an international conspiracy while seeking justice for the murder of his pregnant wife.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rEm96ib0sPiZBADNKBHKBv5bve9.jpg",
                7.3,
                "",
                109,
                "Released",
                false
            )
        )

        detailMovies.add(
            MovieEntity(
                527774,
                "Raya and the Last Dragon",
                "2021-03-03",
                "Long ago, in the fantasy world of Kumandra, humans and dragons lived together in harmony. But when an evil force threatened the land, the dragons sacrificed themselves to save humanity. Now, 500 years later, that same evil has returned and it’s up to a lone warrior, Raya, to track down the legendary last dragon to restore the fractured land and its divided people.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lPsD10PP4rgUGiGR4CCXA6iY0QQ.jpg",
                7.4,
                "A quest to save her world.",
                107,
                "Released",
                false
            )
        )
        return detailMovies
    }

    fun generateDummyTvShow(): List<TvShowResultsItem> {

        val tvshow = ArrayList<TvShowResultsItem>()

        tvshow.add(
            TvShowResultsItem(
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\\\\\"meta-human\\\\\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "The Flash",
                60735,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg"
            )
        )


        tvshow.add(
            TvShowResultsItem(
                "2015",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "Supergirl",
                22,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zsaiq8ZclPuneuN7loLEbsh1ANJ.jpg"
            )
        )

        tvshow.add(
            TvShowResultsItem(
                "2019",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "The Umbrella Academy",
                23,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg"
            )
        )
        return tvshow
    }

    fun generateDummyDetailTv(tv: TvShowEntity, isFav: Boolean): TvDetailWithGenre {
        tv.isFavorite = isFav
        return TvDetailWithGenre(tv, generateDummyTvGenres(tv.tvId))
    }

    fun generateLocalDummyDetailTv(): List<TvShowEntity> {

        val detailTv = ArrayList<TvShowEntity>()

        detailTv.add(
            TvShowEntity(
                60735,
                "The Flash",
                "2014-10-07",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only \\\"meta-human\\\" who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/lJA2RCMfsWoskqlQhXPSLFQGXEJ.jpg",
                146,
                7,
                "Scripted",
                7.7,
                "The fastest man alive.",
                "Returning Series",
                false
            )
        )

        detailTv.add(
            TvShowEntity(
                62688,
                "Supergirl",
                "2015-10-26",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/zsaiq8ZclPuneuN7loLEbsh1ANJ.jpg",
                118,
                6,
                "Scripted",
                6.12,
                "A force against fear",
                "Returning Series",
                false
            )
        )

        detailTv.add(
            TvShowEntity(
                75006,
                "The Umbrella Academy",
                "2019-02-15",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/scZlQQYnDVlnpxFTxaIv2g0BWnL.jpg",
                20,
                2,
                "Scripted",
                8.7,
                "Super. Dysfunctional. Family.",
                "Returning Series",
                false
            )
        )
        return detailTv
    }

    private fun generateDummyMovieGenres(id: Int): List<MovieGenreEntity> {

        val genreItem = ArrayList<MovieGenreEntity>()

        genreItem.add(
            MovieGenreEntity(
                id,
                28,
                "Action"
            )
        )

        genreItem.add(
            MovieGenreEntity(
                id,
                12,
                "Adventure"
            )
        )

        genreItem.add(
            MovieGenreEntity(
                id,
                53,
                "Thriller"
            )
        )

        genreItem.add(
            MovieGenreEntity(
                id,
                10752,
                "War"
            )
        )
        return genreItem
    }

    private fun generateDummyTvGenres(id: Int): List<TvGenreEntity> {

        val genreItem = ArrayList<TvGenreEntity>()

        genreItem.add(
            TvGenreEntity(
                id,
                28,
                "Action"
            )
        )

        genreItem.add(
            TvGenreEntity(
                id,
                12,
                "Adventure"
            )
        )

        genreItem.add(
            TvGenreEntity(
                id,
                53,
                "Thriller"
            )
        )

        genreItem.add(
            TvGenreEntity(
                id,
                10752,
                "War"
            )
        )
        return genreItem
    }
}