package com.mercadolibre.cristhianbonilla.mercadolibre.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.mercadolibre.cristhianbonilla.mercadolibre.R
import com.mercadolibre.cristhianbonilla.mercadolibre.databinding.SearchFragmentBinding
import com.mercadolibre.cristhianbonilla.support.config.binding.fragmentBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    private val binding by fragmentBinding<SearchFragmentBinding>(R.layout.search_fragment)

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val viewModel: SearchViewModel by viewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.run {
        root
    }
}