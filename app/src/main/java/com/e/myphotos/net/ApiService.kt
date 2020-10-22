package com.e.myphotos.net

import com.e.myphotos.data.PhotosSearchResponse
import retrofit2.http.GET


interface ApiService {

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1&text=cats&api_key=55dd726c0b4d19c697782a9307e665db")
    suspend fun fetchImages(): PhotosSearchResponse
}
