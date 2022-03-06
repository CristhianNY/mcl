package com.mercadolibre.cristhianbonilla.mercadolibre.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.mercadolibre.cristhianbonilla.domain.model.ProductModel
import com.mercadolibre.cristhianbonilla.mercadolibre.MainViewModel
import com.mercadolibre.cristhianbonilla.mercadolibre.R
import com.mercadolibre.cristhianbonilla.mercadolibre.databinding.FragmentProductListBinding
import com.mercadolibre.cristhianbonilla.support.config.binding.fragmentBinding
import com.xwray.groupie.GroupieAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ProductListFragment : DaggerFragment() {

    private val adapter by lazy { GroupieAdapter() }

    private val binding by fragmentBinding<FragmentProductListBinding>(R.layout.fragment_product_list)

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private val mainViewModel: MainViewModel by activityViewModels { factory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = binding.run {
        root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpProductsRecyclerView()
        showProducts()
    }

    private fun showProducts() {
        val products = mainViewModel.productList.value?.map {
            ProductItemView(it, ::showProductDetails)
        }

        products?.let {
            adapter.updateAsync(it)
        }
    }

    private fun showProductDetails(productModel: ProductModel) {
        Toast.makeText(requireContext(), "show Details", Toast.LENGTH_LONG).show()
    }

    private fun setUpProductsRecyclerView() {
        binding.rvProductList.adapter = adapter
    }
}