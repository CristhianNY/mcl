package com.mercadolibre.cristhianbonilla.mercadolibre.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
        observeViewModelEvents()
    }

    private fun observeViewModelEvents() {
        viewModel.state.observe(viewLifecycleOwner, ::handleState)
    }

    private fun handleState(state: SearchState) {
        handleLoader(false)
        when (state) {
            is SearchState.ProductSuccess -> {
                Toast.makeText(requireContext(), state.product.toString(), Toast.LENGTH_LONG).show()
            }

            is SearchState.GenericError -> Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.sorry_unknowerror),
                Toast.LENGTH_LONG
            ).show()

            is SearchState.EmptyBox -> Toast.makeText(
                requireContext(),
                requireContext().getString(R.string.please_fill_the_box),
                Toast.LENGTH_LONG
            ).show()

            is SearchState.Loading -> handleLoader(true)
        }
    }

    private fun setUpView() {
        binding.btnFindProduct.setOnClickListener {
            viewModel.getProductsByName(binding.edProductNameValue.text.toString())
        }
    }

    private fun handleLoader(isVisible: Boolean) {
        if (isVisible) {
            binding.loader.visibility = View.VISIBLE
            binding.content.visibility = View.GONE
        } else {
            binding.loader.visibility = View.GONE
            binding.content.visibility = View.VISIBLE
        }
    }
}