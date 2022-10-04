package com.husqvarna.movies.view.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.husqvarna.movies.R
import com.husqvarna.movies.data.model.popular.Result
import com.husqvarna.movies.databinding.MovieListItemBinding
import com.husqvarna.movies.util.CircularProgress
import com.husqvarna.movies.util.GlideApp
import com.husqvarna.movies.view.ui.fragments.MovieListFragmentDirections
import com.squareup.picasso.Picasso

class MovieListAdapter :
    ListAdapter<Result, MovieListAdapter.ViewHolder>(ResultDiffCallBack()) {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        context = parent.context


        return ViewHolder(
            MovieListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val result = getItem(position)
        with(result) {


            GlideApp.with(context)
                .load(this.getMoviePoster())
                .placeholder(CircularProgress.circularProgress(context))
                .into(holder.poster)



            holder.poster.setOnClickListener {
                it.findNavController().navigate(
                    MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(this.id)
                )
            }
        }
    }


    inner class ViewHolder(private val binding: MovieListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val poster = binding.moviePosterImg
    }
}

private class ResultDiffCallBack : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }
}