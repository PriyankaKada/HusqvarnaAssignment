package com.husqvarna.movies.view.ui.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.husqvarna.movies.data.model.popular.Result
import com.husqvarna.movies.databinding.FragmentMovieListBinding
import com.husqvarna.movies.util.Resource
import com.husqvarna.movies.view.ui.adapters.MovieListAdapter
import com.husqvarna.movies.view.viewmodel.MovieListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private val movieListViewModel: MovieListViewModel by viewModels()
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!
    private val movieListAdapter by lazy { MovieListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)

        binding.movieListRecyclerview.adapter = movieListAdapter

        movieListViewModel.result.observe(viewLifecycleOwner,::getPopularMovieList)

        return binding.root
    }

    private fun getPopularMovieList(resource: Resource<List<Result>>){
        when(resource){

            is  Resource.Success -> {
                binding.progressBar.visibility = View.GONE
                movieListAdapter.submitList(resource.data)
            }

            is Resource.Loading -> {
                binding.progressBar.visibility = View.VISIBLE
            }

            is Resource.Error -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}