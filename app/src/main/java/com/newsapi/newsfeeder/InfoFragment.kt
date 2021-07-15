package com.newsapi.newsfeeder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.newsapi.newsfeeder.data.model.Article
import com.newsapi.newsfeeder.databinding.FragmentInfoBinding
import com.newsapi.newsfeeder.presentation.viewmodel.NewsViewModel

class InfoFragment : Fragment() {

    private lateinit var binding: FragmentInfoBinding
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentInfoBinding.bind(view)
        val args: InfoFragmentArgs by navArgs()
        val article: Article = args.selectedArticle

        viewModel = (activity as MainActivity).viewModel

        binding.wvArticle.apply {
            webViewClient = WebViewClient()
            if (!article.url.isNullOrEmpty()) {
                loadUrl(article.url)
            }
        }

        binding.fabSave.setOnClickListener {
            viewModel.saveArticle(article)
            Snackbar.make(view, "Saved Successfully", Snackbar.LENGTH_SHORT).show()
        }
    }

}