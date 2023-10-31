package com.gokhancsml.mvvmnews.ui.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.gokhancsml.mvvmnews.R
import com.gokhancsml.mvvmnews.databinding.FragmentArticleBinding
import com.gokhancsml.mvvmnews.databinding.FragmentSavedNewsBinding
import com.gokhancsml.mvvmnews.databinding.ItemArticlePreviewBinding
import com.gokhancsml.mvvmnews.ui.NewsActivity
import com.gokhancsml.mvvmnews.ui.NewsViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ArticleFragment : Fragment(R.layout.fragment_article) {

    private lateinit var binding: FragmentArticleBinding

    lateinit var viewModel: NewsViewModel
    val args: ArticleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArticleBinding.bind(view)
        viewModel = (activity as NewsActivity).viewModel
        val article = args.article
        binding.webView.apply {
            webViewClient = WebViewClient()
            article.url?.let { loadUrl(it) }
        }

        binding.fab.setOnClickListener {

            lifecycleScope.launch(Dispatchers.IO) {
                viewModel.saveArticle(article)
                Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()

            }
        }
    }
}
