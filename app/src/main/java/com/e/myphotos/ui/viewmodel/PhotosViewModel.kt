package com.e.myphotos.ui.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.e.myphotos.domain.Photo
import com.e.myphotos.net.WebClient
import com.e.myphotos.ui.PhotosAdapter
import com.e.myphotos.ui.ResultActivity
import kotlinx.coroutines.launch


class PhotosViewModel : ViewModel() {

    private val mutablePhotosListLiveData = MutableLiveData<List<Photo>>()
    private val photosListLiveData: LiveData<List<Photo>> = mutablePhotosListLiveData
    private var resultActivity = ResultActivity()

    var photosAdapter = PhotosAdapter { selectedItem: Photo ->
        resultActivity.itemClicked(
            selectedItem,
            resultActivity.applicationContext
        )
    }

    fun loadPhotos(searchText: String?): LiveData<List<Photo>> {
        viewModelScope.launch {
            val searchResponse = WebClient.client.fetchImages(searchText)
            val photosList = searchResponse.photos.photo.map { photo ->
                Photo(
                    id = photo.id,
                    url = "https://farm${photo.farm}.staticflickr.com/${photo.server}/${photo.id}_${photo.secret}.jpg",
                    title = photo.title
                )
            }
            mutablePhotosListLiveData.postValue(photosList)
        }
        return photosListLiveData
    }
}
