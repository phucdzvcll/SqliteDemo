package com.example.sqlitedemo.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.common_android.BaseFragment
import com.example.sqlitedemo.databinding.FragmentAboutThisAppBinding


class AboutThisAppFragment : BaseFragment() {
    lateinit var binding: FragmentAboutThisAppBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutThisAppBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cleanArchitecture.setOnClickListener {
            openBrowser("https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html")
        }
        binding.roomSqlite.setOnClickListener {
            openBrowser("https://developer.android.com/training/data-storage/room")
        }
        binding.gitHub.setOnClickListener {
            openBrowser("https://github.com/phucdzvcll/SqliteDemo")
        }
        binding.mvvm.setOnClickListener {
            openBrowser("https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel")
        }
    }

    private fun openBrowser(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            AboutThisAppFragment()
    }
}