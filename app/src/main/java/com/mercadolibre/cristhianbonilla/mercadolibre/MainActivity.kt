package com.mercadolibre.cristhianbonilla.mercadolibre

import android.os.Bundle
import com.mercadolibre.cristhianbonilla.mercadolibre.databinding.ActivityMainBinding
import com.mercadolibre.cristhianbonilla.support.config.binding.activityBinding
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    private val binding by activityBinding<ActivityMainBinding>(R.layout.activity_main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.run {
            setContentView(root)
        }
    }
}