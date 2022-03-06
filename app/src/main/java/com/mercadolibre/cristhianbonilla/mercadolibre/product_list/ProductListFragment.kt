package com.mercadolibre.cristhianbonilla.mercadolibre.product_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mercadolibre.cristhianbonilla.domain.model.ProductModel
import com.mercadolibre.cristhianbonilla.mercadolibre.MainViewModel
import com.mercadolibre.cristhianbonilla.mercadolibre.R
import com.mercadolibre.cristhianbonilla.mercadolibre.databinding.FragmentProductListBinding
import com.mercadolibre.cristhianbonilla.mercadolibre.databinding.ProductDetailBinding
import com.mercadolibre.cristhianbonilla.support.config.binding.fragmentBinding
import com.mercadolibre.cristhianbonilla.support.config.toCurrency
import com.xwray.groupie.GroupieAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject


class ProductListFragment : DaggerFragment() {

    private val adapter by lazy { GroupieAdapter() }

    private val binding by fragmentBinding<FragmentProductListBinding>(R.layout.fragment_product_list)


    private val productDetailsBottomSheetBinding by fragmentBinding<ProductDetailBinding>(
        R.layout.product_detail
    )

    private var productDetailsBottomShee: BottomSheetDialog? = null

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
        setUpProductDetailsBottomSheet()
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
        productDetailsBottomSheetBinding.tvTitle.text = productModel.title
        productDetailsBottomSheetBinding.tvPriceValue.text = productModel.price.toCurrency()
        productDetailsBottomSheetBinding.tvQueantityValue.text =
            productModel.availableQuantity.toString()
        productDetailsBottomSheetBinding.tvYesOrNot.text =
            (if (productModel.acceptsMercadopago) requireContext().getString(R.string.yes) else requireContext().getString(
                R.string.not
            ))

        productDetailsBottomSheetBinding.ivProduct.run {
            Glide.with(context)
                .load(productModel.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.ic_mercadolibre)
                .error(R.drawable.ic_launcher_background)
                .fallback(com.google.android.material.R.drawable.ic_clock_black_24dp)
                .into(this)
        }

        productDetailsBottomShee?.show()

    }

    private fun setUpProductsRecyclerView() {
        binding.rvProductList.adapter = adapter
    }

    private fun setUpProductDetailsBottomSheet() {
        if (productDetailsBottomShee == null) {
            productDetailsBottomShee =
                BottomSheetDialog(requireContext(), R.style.BottomSheetDialog)
            productDetailsBottomShee?.window?.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            productDetailsBottomSheetBinding?.let { productDetailsBottomShee?.setContentView(it.root) }

            productDetailsBottomShee?.findViewById<FrameLayout>(com.google.android.material.R.id.design_bottom_sheet)
                ?.let {
                    BottomSheetBehavior.from(it).state = BottomSheetBehavior.STATE_EXPANDED
                }

            (productDetailsBottomSheetBinding?.root?.parent as View).setBackgroundResource(R.drawable.background_bottom_sheet)
        }
    }
}