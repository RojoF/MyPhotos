package com.e.myphotos.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.e.myphotos.R
import com.e.myphotos.R.string.buscado
import com.e.myphotos.databinding.ActivityResultBinding
import com.e.myphotos.domain.Photo
import com.e.myphotos.ui.viewmodel.PhotosViewModel


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_result)
        val searchText = intent.getStringExtra(AlarmClock.EXTRA_MESSAGE)
        binding.titleSearch.setText("Has buscado: $searchText")
        val photosViewModel: PhotosViewModel by viewModels()
        binding.photosRecyclerView.adapter = photosViewModel.photosAdapter
        binding.photosRecyclerView.layoutManager = GridLayoutManager(this, 1)
        photosViewModel.loadPhotos(searchText).observe(this,
            { list ->
                with(photosViewModel.photosAdapter) {
                    photos.clear()
                    photos.addAll(list)
                    notifyDataSetChanged()
                }
            })

    }

    fun itemClicked(photo: Photo, cont: Context) {
        val intent = Intent(cont, PhotoActivity::class.java).apply {
            putExtra("photo", photo)
        }
        startActivity(intent)

    }
}
