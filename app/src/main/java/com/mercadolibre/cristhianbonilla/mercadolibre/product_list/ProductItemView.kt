package com.mercadolibre.cristhianbonilla.mercadolibre.product_list

import com.bumptech.glide.Glide
import com.mercadolibre.cristhianbonilla.domain.model.ProductModel
import com.mercadolibre.cristhianbonilla.mercadolibre.R
import com.mercadolibre.cristhianbonilla.mercadolibre.databinding.ProductItemViewBinding
import com.mercadolibre.cristhianbonilla.support.config.toCurrency
import com.xwray.groupie.databinding.BindableItem

class ProductItemView(
    private val item: ProductModel,
    private val action: (action: ProductModel) -> Unit
) : BindableItem<ProductItemViewBinding>() {
    override fun bind(binding: ProductItemViewBinding, position: Int) {
        binding.run {
            tvPrice.text = item.price.toCurrency()
            tvTitle.text = item.title
            ivGoToDetails.setOnClickListener {
                action.invoke(item)
            }

            imageView.run {
                Glide.with(context)
                    .load(item.thumbnail)
                    .centerCrop()
                    .placeholder(R.drawable.ic_mercadolibre)
                    .error(R.drawable.ic_launcher_background)
                    .fallback(com.google.android.material.R.drawable.ic_clock_black_24dp)
                    .into(imageView)
            }
        }
    }

    override fun getLayout() = R.layout.product_item_view
}
