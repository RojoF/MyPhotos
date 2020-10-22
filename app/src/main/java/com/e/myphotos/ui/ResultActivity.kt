package com.e.myphotos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.e.myphotos.R
import com.e.myphotos.databinding.ActivityResultBinding
import com.e.myphotos.domain.Photo


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
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
