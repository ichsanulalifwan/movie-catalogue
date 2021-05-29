package com.dicoding.picodiploma.moviecatalogue3.ui.tvshow

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.moviecatalogue3.data.source.local.entity.tvshow.TvShowEntity
import com.dicoding.picodiploma.moviecatalogue3.databinding.FragmentTvShowBinding
import com.dicoding.picodiploma.moviecatalogue3.ui.adapter.TvShowAdapter
import com.dicoding.picodiploma.moviecatalogue3.ui.detail.DetailTvShowActivity
import com.dicoding.picodiploma.moviecatalogue3.viewmodel.ViewModelFactory
import com.dicoding.picodiploma.moviecatalogue3.vo.Status

class TvShowFragment : Fragment() {

    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var tvAdapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentTvShowBinding = FragmentTvShowBinding.inflate(layoutInflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {

            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TvShowViewModel::class.java]

            tvAdapter = TvShowAdapter()
            setupRecyclerView()
            showLoading(true)

            viewModel.getPopularTvShow().observe(viewLifecycleOwner, { tvShow ->
                if (tvShow != null) {
                    when (tvShow.status) {
                        Status.LOADING -> showLoading(true)
                        Status.SUCCESS -> {
                            tvAdapter.submitList(tvShow.data)
                            showLoading(false)
                        }
                        Status.ERROR -> {
                            showLoading(false)
                            Toast.makeText(context, "Something Wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            onTvShowSelected()
        }
    }

    private fun setupRecyclerView() {
        with(fragmentTvShowBinding.rvTvShow) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }

    private fun onTvShowSelected() {
        tvAdapter.setOnItemClickListener(object : TvShowAdapter.OnItemClickListener {
            override fun onTvClicked(tv: TvShowEntity) {
                val intent = Intent(context, DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.EXTRA_TV_ID, tv.tvId)
                startActivity(intent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            fragmentTvShowBinding.progressBar.visibility = View.VISIBLE
        } else {
            fragmentTvShowBinding.progressBar.visibility = View.GONE
        }
    }
}