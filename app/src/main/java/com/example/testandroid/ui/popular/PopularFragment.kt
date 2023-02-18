package com.example.testandroid.ui.popular

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testandroid.data.model.Movie
import com.example.testandroid.data.remote.MovieDataSource
import com.example.testandroid.databinding.FragmentPopularBinding
import com.example.testandroid.repository.ApiClient
import com.example.testandroid.repository.MovieRepositoryImpl
import com.example.testandroid.utils.Resource


class PopularFragment : Fragment(), PopularMovieItemAdapter.OnMovieClickListener {

    private var _binding: FragmentPopularBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModels<PopularViewModel> {
        PopularViewModelFactory(MovieRepositoryImpl(
            MovieDataSource(ApiClient.apiServices)
        ))
    }

    private lateinit var popularMovieItemAdapter: PopularMovieItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPopularBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvMovies.layoutManager = LinearLayoutManager(context)

        viewModel.fetchPopularMovies().observe(viewLifecycleOwner, Observer {
            when (it) {
                is Resource.Loading -> {
                    Log.e("fetchPopularMovies", "Loading")
                }
                is Resource.Success -> {
                    Log.e("fetchPopularMovies", "Success")
                    popularMovieItemAdapter = PopularMovieItemAdapter(it.data.results, this@PopularFragment)
                    binding.rvMovies.adapter = popularMovieItemAdapter
                }
                is Resource.Failure -> {
                    Log.e("fetchPopularMovies", "Failure: $it.exception ")
                    Toast.makeText(requireContext(), "Failure: ${it.exception}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onMovieClick(movie: Movie) {
        TODO("Not yet implemented")
    }
}