package com.example.newsapp.ui.screens.science

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.newsapp.R
import com.example.newsapp.core.ResultWrapperUI
import com.example.newsapp.core.Toast.showToast
import com.example.newsapp.databinding.FragmentScienceBinding
import com.example.newsapp.domain.models.News
import com.example.newsapp.ui.adapters.NewsAdapterDelegate
import com.example.newsapp.ui.adapters.NewsRecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ScienceFragment : Fragment(), NewsAdapterDelegate {

    private lateinit var binding: FragmentScienceBinding
    private lateinit var viewModel: ScienceViewModel

    private var newsRecyclerViewAdapter: NewsRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentScienceBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ScienceViewModel::class.java]

        newsRecyclerViewAdapter = NewsRecyclerViewAdapter(this)
        binding.scienceRV.adapter = newsRecyclerViewAdapter


        lifecycleScope.launch {
            viewModel.scienceNews.collectLatest { result ->
                when (result) {
                    is ResultWrapperUI.Success -> {
                        binding.loading.root.visibility = View.GONE
                        newsRecyclerViewAdapter?.submitList(result.data)
                    }

                    ResultWrapperUI.Loading -> {
                        binding.loading.root.visibility = View.VISIBLE
                    }

                    is ResultWrapperUI.Error -> showToast(R.string.error)
                }

            }

        }
    }

    override fun onNewsClicked(news: News) {
        startActivity(Intent(Intent.ACTION_VIEW, news.url))
    }

}