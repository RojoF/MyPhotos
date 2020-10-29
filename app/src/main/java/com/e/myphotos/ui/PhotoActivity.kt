package com.e.myphotos.ui

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import com.e.myphotos.R
import com.e.myphotos.databinding.ActivityPhotoBinding
import com.e.myphotos.domain.Photo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_photo.*
import kotlinx.android.synthetic.main.card.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class PhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhotoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo)
        binding.apply {
            val photoView = imageViewfull
            val photo = intent.getSerializableExtra("photo") as? Photo

            ButtonShare.setOnClickListener {
                comparteView(viewShare = card)

            }
            photoView.isZoomable
            photoView.scaleType

            if (photo != null) {
                titleFullphoto.text = photo.title
                idFullphoto.text = photo.id
            }
            Picasso.get().load(photo?.url)
                .resize(IMAGE_SIDE_PX, IMAGE_SIDE_PX)
                .centerCrop()
                .into(imageViewfull)
        }
    }


    private fun comparteView(viewShare: View) {
        // Creamos un bitmap con el tamaño de la vista
        val bitmap = Bitmap.createBitmap(
            viewShare.width,
            viewShare.height, Bitmap.Config.ARGB_8888
        )
        // Creamos el canvas para pintar en el bitmap
        val canvas = Canvas(bitmap)
        // Pintamos el contenido de la vista en el canvas y así en el bitmap
        viewShare.draw(canvas)
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        var uriF: Uri? = null
        try {
            val f = File.createTempFile(
                "sharedImage", ".jpg", externalCacheDir
            )
            f.deleteOnExit()
            val fo = FileOutputStream(f)
            fo.write(stream.toByteArray())
            fo.close()
            uriF = FileProvider.getUriForFile(
                this,
                this.applicationContext.packageName + ".provider",
                f
            );
            val shareIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM, uriF)
                addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                type = "image/jpeg"
            }

            startActivity(
                Intent.createChooser(
                    shareIntent,
                    resources.getText(R.string.send_to)
                )
            )


        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


}


