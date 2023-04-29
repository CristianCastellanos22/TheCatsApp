package com.cristian.castellanos.thecatsapp.views

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.cristian.castellanos.thecatsapp.data.ApiResponseStatus
import com.cristian.castellanos.thecatsapp.databinding.ActivityMainBinding
import com.cristian.castellanos.thecatsapp.viewmodels.CatListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val adapter: CatAdapter = CatAdapter()
    private val viewModel: CatListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.contentRecycler.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.contentRecycler.setHasFixedSize(true)
        wheel()
        observer()
    }

    private fun wheel() {
        viewModel.status.observe(this) {
            with(binding) {
                when (it) {
                    is ApiResponseStatus.Error -> {
                        loadingWheel.visibility = View.GONE
                        Toast.makeText(this@MainActivity, it.messageId, Toast.LENGTH_LONG).show()
                    }
                    is ApiResponseStatus.Loading -> loadingWheel.visibility = View.VISIBLE
                    is ApiResponseStatus.Success -> loadingWheel.visibility = View.GONE
                }
            }
        }
    }

    private fun observer() {
        with(binding) {
            viewModel.catList.observe(this@MainActivity) {
                when {
                    !it.isNullOrEmpty() -> {
                        adapter.submitList(it)
                        contentRecycler.adapter = adapter
                    }
                }
            }
        }
    }
}