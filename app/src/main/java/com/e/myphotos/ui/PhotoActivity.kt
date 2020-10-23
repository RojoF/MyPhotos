package com.e.myphotos.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.e.myphotos.R
import com.e.myphotos.databinding.ActivityPhotoBinding
import com.e.myphotos.domain.Photo
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.card.view.*


class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        val photo = intent.getSerializableExtra("photo") as? Photo

        val photoView = binding.imageViewfull as PhotoView
        photoView.isZoomable
        photoView.scaleType
        if (photo != null) {
            binding.titleFullphoto.text = photo.title
            binding.idFullphoto.text = photo.id
        }
        Picasso.get().load(photo?.url)
            .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
            .centerCrop()
            .into(binding.imageViewfull)
    }


}


