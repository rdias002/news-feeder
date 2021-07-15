package com.newsapi.newsfeeder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.newsapi.newsfeeder.data.util.Resource
import com.newsapi.newsfeeder.databinding.FragmentNewsBinding
import com.newsapi.newsfeeder.presentation.adapter.NewsAdapter
import com.newsapi.newsfeeder.presentation.viewmodel.NewsViewModel

class NewsFragment : Fragment() {
    private var page: Int = 1
    private var pages: Int = 0
    private val country: String = "us"
    private var isScrolling = false
    private var isLoading = false
    private var isLastPage: Boolean = false

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        newsAdapter = (activity as MainActivity).adapter
        binding = FragmentNewsBinding.bind(view)

        initRecyclerView()
        viewNewsList()
        setSearchView()
    }

    private fun initRecyclerView() {
        binding.rvNewsList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = newsAdapter
            addOnScrollListener(onScrollListener)
        }
        newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("selected_article", it)
            }
            findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
        }
    }

    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = (recyclerView.layoutManager) as LinearLayoutManager
            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            val hasReachedToEnd = topPosition + visibleItems >= sizeOfCurrentList
            val shouldPaginate = !isLoading && hasReachedToEnd && isScrolling
            if (shouldPaginate) {
                page++
                viewModel.getNewsHeadlines(country, page)
                isScrolling = false
            }

        }
    }

    private fun viewNewsList() {
        viewModel.getNewsHeadlines(country, page)
        viewModel.newsHeadlinesLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { response ->
                        newsAdapter.differ.submitList(response.articles.toList())
                        pages = if (response.totalResults % 38 == 0) {
                            response.totalResults / 38
                        } else {
                            response.totalResults / 38 + 1
                        }
                        isLastPage = page == pages
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Snackbar.make(binding.root, it.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun setSearchView() {
        binding.svSearchNews.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                page = 1
                viewModel.searchNews(country, query.toString(), page)
                viewSearchedNewsList()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                /* MainScope().launch {
                     delay(2000)
                     viewModel.searchNews(country, newText.toString(), page)
                     viewSearchedNewsList()
                 }*/
                return false
            }

        })

        binding.svSearchNews.setOnCloseListener {
            initRecyclerView()
            viewNewsList()
            false
        }
    }

    private fun viewSearchedNewsList() {
        viewModel.searchedNewsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { response ->
                        newsAdapter.differ.submitList(response.articles.toList())
                        pages = if (response.totalResults % 38 == 0) {
                            response.totalResults / 38
                        } else {
                            response.totalResults / 38 + 1
                        }
                        isLastPage = page == pages
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Error -> {
                    hideProgressBar()
                    Snackbar.make(binding.root, it.message.toString(), Snackbar.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }
}