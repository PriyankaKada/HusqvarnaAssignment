package com.husqvarna.movies.view.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.husqvarna.movies.R
import com.husqvarna.movies.data.model.detail.DetailMovieResponse
import com.husqvarna.movies.databinding.FragmentMovieDetailBinding
import com.husqvarna.movies.util.CircularProgress
import com.husqvarna.movies.util.GlideApp
import com.husqvarna.movies.view.viewmodel.MovieDetailViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private val movieDetailViewModel: MovieDetailViewModel by viewModels()
    val args: MovieDetailFragmentArgs by navArgs()
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        movieDetailViewModel.getMoviesDetail(args.movieId.toString())
        movieDetailViewModel.movieDetail.observe(viewLifecycleOwner,::setDetailsData)
        return binding.root
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setDetailsData(detailMovieResponse: DetailMovieResponse){
        binding.movieOverviewTxt.text = detailMovieResponse.overview
        GlideApp.with(requireContext())
            .load(detailMovieResponse.getMovieBanner())
            .placeholder(CircularProgress.circularProgress(requireContext()))
            .into(binding.moviePosterImg)
        binding.movieRatingBar.rating = detailMovieResponse.voteAverage.toFloat()
        binding.movieReleaseDateTxt.text = detailMovieResponse.releaseDate
        binding.movieTitleTxt.text = detailMovieResponse.originalTitle
    }
}