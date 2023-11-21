package com.example.newsapp.ui.screens.entertainment

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
import com.example.newsapp.databinding.FragmentEntertainmentBinding
import com.example.newsapp.domain.models.News
import com.example.newsapp.ui.adapters.NewsAdapterDelegate
import com.example.newsapp.ui.adapters.NewsRecyclerViewAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class EntertainmentFragment : Fragment(), NewsAdapterDelegate {

    private lateinit var binding: FragmentEntertainmentBinding
    private lateinit var viewModel: EntertainmentViewModel

    private var newsRecyclerViewAdapter: NewsRecyclerViewAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEntertainmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[EntertainmentViewModel::class.java]

        newsRecyclerViewAdapter = NewsRecyclerViewAdapter(this)
        binding.entertainmentRV.adapter = newsRecyclerViewAdapter

        lifecycleScope.launch {
            viewModel.entertainmentNews.collectLatest { result ->
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