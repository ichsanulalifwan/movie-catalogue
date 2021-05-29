package com.dicoding.picodiploma.moviecatalogue3.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.MovieAdapter.Companion.IMAGE_PREFIX
import com.dicoding.picodiploma.moviecatalogue3.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.moviecatalogue3.R
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.databinding.ActivityDetailTvShowBinding
import com.dicoding.picodiploma.moviecatalogue3.model.GenreData
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.GenresAdapter
import com.dicoding.picodiploma.moviecatalogue3.utils.DataMapper
import com.dicoding.picodiploma.moviecatalogue3.vo.Status

class DetailTvShowActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTvShowBinding
    private lateinit var viewModel: DetailTvShowViewModel
    private lateinit var genresAdapter: GenresAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailTvShowBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory)[DetailTvShowViewModel::class.java]

        genresAdapter = GenresAdapter()

        val extras = intent.extras
        if (extras != null) {
            val tvId = extras.getInt(EXTRA_TV_ID)
            viewModel.setSelectedTvShow(tvId)

            viewModel.detailTv.observe(this, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            if (tvShow.data != null) {
                                populateData(tvShow.data.mTv)
                                getGenres(DataMapper.mapTvGenreEntityToModel(tvShow.data.mGenre))
                                setFavouriteState(tvShow.data.mTv.isFavorite)
                                onFavButtonClicked(tvShow.data.mTv)
                                showLoading(false)
                            }
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(
                                applicationContext,
                                "Something Wrong",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            })
        }
    }

    private fun populateData(tvshow: TvShowEntity) {
        binding.apply {
            tvTitleTvShow.text = tvshow.name
            tvFirstAir.text = tvshow.firstAirDate
            tvSeason.text = tvshow.numberOfSeasons.toString()
            tvEpisode.text = tvshow.numberOfEpisodes.toString()
            tvTaglineTv.text = tvshow.tagline
            tvVoteTv.text = tvshow.voteAverage.toString()
            tvStatusTv.text = tvshow.status
            tvType.text = tvshow.type
            tvOverview.text = tvshow.overview

            Glide.with(this@DetailTvShowActivity)
                .load(IMAGE_PREFIX + tvshow.posterPath)
                .transform(RoundedCorners(20))
                .apply(RequestOptions.placeholderOf(R.drawable.movie_poster))
                .into(imgPosterTv)
        }
    }

    private fun getGenres(genres: List<GenreData>) {
        genresAdapter.setData(genres)
        with(binding.rvGenres) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = genresAdapter
        }
    }

    private fun onFavButtonClicked(data: TvShowEntity) {
        binding.btnFav.setOnClickListener {
            viewModel.setTvFavorite()
            if (!data.isFavorite) Toast.makeText(
                this,
                data.name + " " + "Added to Favorite",
                Toast.LENGTH_SHORT
            ).show()
            else Toast.makeText(
                this,
                data.name + " " + "Deleted from Favorite",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setFavouriteState(state: Boolean) {
        if (state) {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_24)
        } else {
            binding.btnFav.setBackgroundResource(R.drawable.ic_baseline_favorite_border_24)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        const val EXTRA_TV_ID = "EXTRA_TV_ID"
    }
}