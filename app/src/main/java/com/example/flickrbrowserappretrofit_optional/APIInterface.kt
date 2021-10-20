package com.example.currencyconverterapp


import com.example.flickrbrowserappretrofit_optional.Filcker
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface APIInterface {
   // @Headers("Content-Type: application/json")
       @GET("/services/rest/?method=flickr.photos.search&api_key=5cfbf3eb82179f031c7e1b5d82759cdb&format=json&nojsoncallback=1")
       fun getFilker(@Query("tags")tags: String): Call<Filcker>?

}