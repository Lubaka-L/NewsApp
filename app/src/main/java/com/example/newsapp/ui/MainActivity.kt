package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.newsapp.ui.adapters.TabFragmentsAdapter
import com.example.newsapp.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initial()
    }

    private fun initial() {
        val tabFragmentsAdapter = TabFragmentsAdapter(this)
        binding.viewPager.adapter = tabFragmentsAdapter
        binding.tabBar.tabIconTint = null

        TabLayoutMediator(binding.tabBar, binding.viewPager) { tab, pos ->
            tab.text = tabFragmentsAdapter.getTabTitle(pos)
        }.attach()
    }
}