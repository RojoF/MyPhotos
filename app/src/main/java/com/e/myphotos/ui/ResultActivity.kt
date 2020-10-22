package com.example.android.searchdebounce.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.android.searchdebounce.R
import com.example.android.searchdebounce.databinding.ActivityPhotosBinding
import com.example.android.searchdebounce.domain.Photo


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photos)
        val photosViewModel: PhotosViewModel by viewModels()

        binding.photosRecyclerView.adapter = photosViewModel.photosAdapter
        binding.photosRecyclerView.layoutManager = GridLayoutManager(this, 1)
        photosViewModel.loadPhotos().observe(this,
            Observer<List<Photo>> { list ->
                with(photosViewModel.photosAdapter) {
                    photos.clear()
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })
    }
}
