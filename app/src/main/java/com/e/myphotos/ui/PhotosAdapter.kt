package com.e.myphotos.ui


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.e.myphotos.R
import com.e.myphotos.domain.Photo
import com.github.chrisbanes.photoview.PhotoView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card.view.*


class PhotosAdapter(
    val photos: MutableList<Photo> = mutableListOf(),
    private val clickListener: (Photo) -> Unit
) :
    RecyclerView.Adapter<PhotosAdapter.PhotosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        return PhotosViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.card,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = photos.size


    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bind(photos[position], clickListener)


    }

    inner class PhotosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        fun bind(photo: Photo, clickListener: (Photo) -> Unit) {
            val photoView = itemView.imageViewfull as PhotoView
            photoView.isZoomable
            photoView.scaleType
            itemView.title_fullphoto.text = photo.title
            itemView.id_fullphoto.text = photo.id

            Picasso.get().load(photo.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .into(itemView.imageViewfull)

            itemView.card.setOnClickListener() {
                clickListener(photo)
            }

        }


    }

}


const val IMAGE_SIDE_PX = 250
