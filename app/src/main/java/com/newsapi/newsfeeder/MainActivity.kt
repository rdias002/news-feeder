package com.newsapi.newsfeeder

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.newsapi.newsfeeder.databinding.ActivityMainBinding
import com.newsapi.newsfeeder.presentation.adapter.NewsAdapter
import com.newsapi.newsfeeder.presentation.viewmodel.NewsViewModel
import com.newsapi.newsfeeder.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: NewsViewModelFactory

    @Inject
    lateinit var adapter: NewsAdapter

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomMenu.setupWithNavController(navController)

        viewModel = ViewModelProvider(this, viewModelFactory).get(NewsViewModel::class.java)
    }
}