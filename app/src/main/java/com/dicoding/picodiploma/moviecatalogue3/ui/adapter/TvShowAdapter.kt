package com.dicoding.picodiploma.moviecatalogue3.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dicoding.picodiploma.moviecatalogue3.R
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.databinding.ItemListBinding
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.MovieAdapter.Companion.IMAGE_PREFIX

class TvShowAdapter :
    PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickListener: OnItemClickListener

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemMovieBinding = ItemListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return TvShowViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tv = getItem(position)
        if (tv != null) {
            holder.bind(tv)
        }
    }

    inner class TvShowViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(tv: TvShowEntity) {
            with(binding) {
                tvTitle.text = tv.name
                tvRelaseDate.text = tv.firstAirDate
                tvOverviewItem.text = tv.overview
                itemView.setOnClickListener {
                    onItemClickListener.onTvClicked(tv)
                }

                Glide.with(itemView.context)
                    .load(IMAGE_PREFIX + tv.posterPath)
                    .centerCrop()
                    .placeholder(R.drawable.movie_poster)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imgPoster)
            }
        }
    }

    interface OnItemClickListener {
        fun onTvClicked(tv: TvShowEntity)
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.tvId == newItem.tvId
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}